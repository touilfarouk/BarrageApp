package com.codewithfk.domain.storage

import kotlinx.coroutines.flow.Flow

interface RememberMeStorage {
    val rememberMeFlow: Flow<Boolean>
    suspend fun getRememberMe(): Boolean
    suspend fun setRememberMe(enabled: Boolean)
    suspend fun getSavedEmail(): String?
    suspend fun setSavedEmail(email: String?)
}
