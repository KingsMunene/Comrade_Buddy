package com.example.comradebuddy.presentation.sign_in

data class SignInState(
    val signInIsSuccessful: Boolean = false,
    val signInErrorMessage: String? = null
)