package com.codewithfk.presentation.feature.programmes

import com.codewithfk.domain.model.Programme

data class ProgrammesUiState(
    val programmes: List<Programme> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val sessionExpired: Boolean = false
)
