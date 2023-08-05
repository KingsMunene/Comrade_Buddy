package com.example.comradebuddy.presentation.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comradebuddy.presentation.HomeScreen
import com.example.comradebuddy.presentation.notes.NoteBookViewModel
import com.example.comradebuddy.presentation.sign_in.GoogleAuthUiClient
import com.example.comradebuddy.presentation.sign_in.SignInScreen
import com.example.comradebuddy.presentation.sign_in.SignInViewModel
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch


enum class AppNavigation{
    SPLASH,
    LOGIN,
    HOME,
    UNITS,
    NOTES
}

@Composable
fun StartScreen(
    navController: NavHostController = rememberNavController(),
    signInViewModel: SignInViewModel = viewModel(),
    noteBookViewModel: NoteBookViewModel = viewModel(),
    lifecycle: LifecycleCoroutineScope,
    context: Context

) {
    val state by signInViewModel.state.collectAsStateWithLifecycle()
    val noteState = noteBookViewModel._uiState.collectAsState()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    //Check if a user is already signed in
    LaunchedEffect(key1 = Unit ){

        if(googleAuthUiClient.getSignedInUser() != null){
            navController.navigate(AppNavigation.HOME.name)
        }
    }
    //Nav host for the app
    NavHost(navController, startDestination = AppNavigation.LOGIN.name){

        composable(AppNavigation.LOGIN.name){
            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate("profile")
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
                        lifecycle.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            signInViewModel.onSignInResults(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.signInIsSuccessful) {
                if(state.signInIsSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(AppNavigation.HOME.name)
                    signInViewModel.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = {
                    lifecycle.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }

        composable(AppNavigation.HOME.name) {
            HomeScreen(
                courseList = noteState.value.courses,
                courseClicked = {
                        courseName -> noteBookViewModel.getUnits(courseName)},
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycle.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                }
            )
        }
    }

}
