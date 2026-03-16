package com.codewithfk.presentation.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codewithfk.domain.usecase.ClearTokenUseCase
import com.codewithfk.domain.usecase.GetTokenUseCase
import com.codewithfk.domain.usecase.ObserveTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TokenViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val clearTokenUseCase: ClearTokenUseCase,
    private val observeTokenUseCase: ObserveTokenUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TokenUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            observeTokenUseCase.execute().collectLatest { token ->
                val roles = token?.let { JwtUtils.extractRoles(it) }
                _uiState.value = _uiState.value.copy(
                    token = token,
                    roles = roles,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }
    }

    fun loadToken() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                val token = getTokenUseCase.execute()
                val roles = token?.let { JwtUtils.extractRoles(it) }
                _uiState.value = _uiState.value.copy(
                    token = token,
                    roles = roles,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message, isLoading = false)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)
            try {
                clearTokenUseCase.execute()
                _uiState.value = _uiState.value.copy(token = null, roles = null, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message, isLoading = false)
            }
        }
    }
}
