package com.codewithfk.travenor.storage

import com.codewithfk.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.Foundation.NSUserDefaults

class IosTokenStorage : TokenStorage {

    private val defaults = NSUserDefaults.standardUserDefaults
    private val key = "access_token"
    private val state = MutableStateFlow(defaults.stringForKey(key))

    override val tokenFlow: Flow<String?> = state

    override suspend fun getToken(): String? {
        return state.value
    }

    override suspend fun saveToken(token: String) {
        defaults.setObject(token, forKey = key)
        state.value = token
    }

    override suspend fun clearToken() {
        defaults.removeObjectForKey(key)
        state.value = null
    }
}
