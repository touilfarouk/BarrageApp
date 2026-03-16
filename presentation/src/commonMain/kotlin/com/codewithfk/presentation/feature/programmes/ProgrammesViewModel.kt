package com.codewithfk.presentation.feature.programmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithfk.domain.usecase.GetAllProgrammesUseCase
import com.codewithfk.domain.usecase.GetTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProgrammesViewModel(
    private val getAllProgrammesUseCase: GetAllProgrammesUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProgrammesUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        loadProgrammes()
    }

    fun loadProgrammes() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            val token = getTokenUseCase.execute()
            if (token.isNullOrBlank()) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Not authenticated"
                )
                return@launch
            }
            val result = getAllProgrammesUseCase.execute(token)
            result.onSuccess { programmes ->
                _uiState.value = _uiState.value.copy(
                    programmes = programmes,
                    isLoading = false
                )
            }.onFailure { error ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = error.message,
                    isLoading = false
                )
            }
        }
    }
}
