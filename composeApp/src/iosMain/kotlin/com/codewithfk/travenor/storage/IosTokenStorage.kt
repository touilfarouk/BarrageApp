package com.codewithfk.travenor.storage

import com.codewithfk.domain.storage.RememberMeStorage
import com.codewithfk.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.Foundation.NSUserDefaults

class IosTokenStorage : TokenStorage, RememberMeStorage {

    private val defaults = NSUserDefaults.standardUserDefaults
    private val key = "access_token"
    private val rememberMeKey = "remember_me"
    private val state = MutableStateFlow(defaults.stringForKey(key))
    private val rememberMeState = MutableStateFlow(defaults.boolForKey(rememberMeKey))

    override val tokenFlow: Flow<String?> = state
    override val rememberMeFlow: Flow<Boolean> = rememberMeState

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

    override suspend fun getRememberMe(): Boolean {
        return rememberMeState.value
    }

    override suspend fun setRememberMe(enabled: Boolean) {
        defaults.setBool(enabled, forKey = rememberMeKey)
        rememberMeState.value = enabled
    }
}
