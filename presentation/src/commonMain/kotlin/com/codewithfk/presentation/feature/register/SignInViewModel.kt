package com.codewithfk.presentation.feature.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithfk.domain.storage.RememberMeStorage
import com.codewithfk.domain.storage.TokenStorage
import com.codewithfk.domain.usecase.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val loginUseCase: SignInUseCase,
    private val tokenStorage: TokenStorage,
    private val rememberMeStorage: RememberMeStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState = _uiState.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _rememberMe = MutableStateFlow(false)
    val rememberMe = _rememberMe.asStateFlow()

    init {
        viewModelScope.launch {
            val rememberMe = rememberMeStorage.getRememberMe()
            _rememberMe.value = rememberMe
            if (rememberMe) {
                val savedEmail = rememberMeStorage.getSavedEmail()
                if (!savedEmail.isNullOrBlank()) {
                    _email.value = savedEmail
                }
            }
        }
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        if (_rememberMe.value) {
            viewModelScope.launch {
                rememberMeStorage.setSavedEmail(newEmail)
            }
        }
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onRememberMeChange(enabled: Boolean) {
        _rememberMe.value = enabled
        viewModelScope.launch {
            rememberMeStorage.setRememberMe(enabled)
            if (enabled) {
                rememberMeStorage.setSavedEmail(_email.value)
            } else {
                rememberMeStorage.setSavedEmail(null)
            }
        }
    }

    fun signIn() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val result = loginUseCase.execute(_email.value, _password.value)
            result.onSuccess { token ->
                tokenStorage.saveToken(token.accessToken)
                if (_rememberMe.value) {
                    rememberMeStorage.setSavedEmail(_email.value)
                }
                _uiState.value = _uiState.value.copy(token = token, isLoading = false)
            }.onFailure { error ->
                _uiState.value =
                    _uiState.value.copy(errorMessage = error.message, isLoading = false)
            }
        }
    }

}
