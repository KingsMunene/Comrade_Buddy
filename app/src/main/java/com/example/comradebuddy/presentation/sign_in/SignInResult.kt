package com.example.comradebuddy.presentation.sign_in

//Data classes for holding information sent back about the user
data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val userName: String?,
    val profilePictureUrl: String?
)
