package com.codewithfk.travenor.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.codewithfk.domain.storage.RememberMeStorage
import com.codewithfk.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_store")

class AndroidTokenStorage(
    private val context: Context
) : TokenStorage, RememberMeStorage {

    private val tokenKey: Preferences.Key<String> = stringPreferencesKey("access_token")
    private val rememberMeKey: Preferences.Key<Boolean> = booleanPreferencesKey("remember_me")

    override val tokenFlow: Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[tokenKey] }

    override val rememberMeFlow: Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[rememberMeKey] ?: false }

    override suspend fun getToken(): String? {
        return tokenFlow.first()
    }

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    override suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(tokenKey)
        }
    }

    override suspend fun getRememberMe(): Boolean {
        return rememberMeFlow.first()
    }

    override suspend fun setRememberMe(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[rememberMeKey] = enabled
        }
    }
}
