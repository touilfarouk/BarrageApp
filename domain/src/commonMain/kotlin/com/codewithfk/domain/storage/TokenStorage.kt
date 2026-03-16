package com.codewithfk.domain.storage

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    val tokenFlow: Flow<String?>
    suspend fun getToken(): String?
    suspend fun saveToken(token: String)
    suspend fun clearToken()
}
