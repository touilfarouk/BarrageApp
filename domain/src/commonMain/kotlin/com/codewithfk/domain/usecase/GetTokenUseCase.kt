package com.codewithfk.domain.usecase

import com.codewithfk.domain.storage.TokenStorage

class GetTokenUseCase(private val tokenStorage: TokenStorage) {
    suspend fun execute(): String? = tokenStorage.getToken()
}
