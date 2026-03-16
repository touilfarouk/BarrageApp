package com.codewithfk.presentation.feature.auth

data class TokenUiState(
    val token: String? = null,
    val roles: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
