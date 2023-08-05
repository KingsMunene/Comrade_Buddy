package com.example.comradebuddy.presentation.sign_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {
    private val _state = MutableStateFlow(SignInState())

    val state = _state.asStateFlow()

    fun onSignInResults(result: SignInResult) {
        _state.update { it.copy(
            signInIsSuccessful = result.data != null,
            signInErrorMessage = result.errorMessage
        ) }
    }

    fun resetState(){
        _state.update { SignInState()}
    }

}