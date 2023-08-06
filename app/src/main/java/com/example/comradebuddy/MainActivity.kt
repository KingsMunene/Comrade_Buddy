package com.example.comradebuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope

import com.example.comradebuddy.presentation.navigation.StartScreen

import com.example.comradebuddy.ui.theme.ComradeBuddyTheme

enum class AppNavigation{
    SPLASH,
    LOGIN,
    HOME,
    UNITS,
    NOTES
}



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComradeBuddyTheme {
                // A surface container using the 'background' color from the theme
                StartScreen(
                    lifecycle = lifecycleScope,
                    context = applicationContext
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComradeBuddyTheme {
        //SignInScreen()
    }
}