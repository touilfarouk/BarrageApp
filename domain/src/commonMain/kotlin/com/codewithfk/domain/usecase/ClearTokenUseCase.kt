package com.codewithfk.domain.usecase

import com.codewithfk.domain.storage.TokenStorage

class ClearTokenUseCase(private val tokenStorage: TokenStorage) {
    suspend fun execute() = tokenStorage.clearToken()
}
