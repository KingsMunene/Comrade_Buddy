package com.example.comradebuddy.presentation.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import com.example.comradebuddy.presentation.notes.PdfDocViewer
import com.example.comradebuddy.presentation.notes.PdfViewModel
import com.example.comradebuddy.presentation.notes.UnitsList
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
    pdfViewModel: PdfViewModel = viewModel(),
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


    //Nav host for the app
    NavHost(
        navController,
        startDestination = AppNavigation.LOGIN.name
    ){

        // Composable for the Login Screen
        composable(AppNavigation.LOGIN.name){
            //Check if a user is already signed in
            LaunchedEffect(key1 = Unit ){
                if(googleAuthUiClient.getSignedInUser() != null){
                    navController.popBackStack()
                    navController.navigate(AppNavigation.HOME.name)
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
                   // navController.popBackStack()
                    navController.navigate(AppNavigation.HOME.name)
                    signInViewModel.resetState()
                }

            }
            // Call the Sign In Screen
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

        // Composable for the Home Screen that shows the Courses
        composable(AppNavigation.HOME.name) {
            HomeScreen(
                courseList = noteState.value.courses,
                courseClicked = {
                        courseName -> noteBookViewModel.units = noteBookViewModel.getUnits(courseName)
                        navController.navigate(AppNavigation.UNITS.name
                    ) },
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
                        navController.navigate(AppNavigation.LOGIN.name)
                    }
                }
            )
        }

        // Composable for showing units available for the selected course
        composable(AppNavigation.UNITS.name) {
            UnitsList(
                // Get the units set when a course is clicked
                units = noteBookViewModel.units ,
                // When a unit is clicked the file name is passed and we call the pdf viewer composable method
                onUnitClicked = {
                    fileName -> noteBookViewModel.fileName = fileName
                    navController.navigate(AppNavigation.NOTES.name)
                })
        }

        //Notes view composable method
        composable(AppNavigation.NOTES.name){
            PdfDocViewer(
                fileName = noteBookViewModel.fileName,
                pdfViewModel = pdfViewModel
            )
        }
    }

}
