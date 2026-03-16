package com.codewithfk.presentation.feature.register

import com.codewithfk.domain.model.AuthTokenModel


data class SignInUiState(
    val token: AuthTokenModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
