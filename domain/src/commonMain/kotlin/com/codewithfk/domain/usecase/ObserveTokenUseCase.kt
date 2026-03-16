package com.codewithfk.domain.usecase

import com.codewithfk.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow

class ObserveTokenUseCase(private val tokenStorage: TokenStorage) {
    fun execute(): Flow<String?> = tokenStorage.tokenFlow
}
