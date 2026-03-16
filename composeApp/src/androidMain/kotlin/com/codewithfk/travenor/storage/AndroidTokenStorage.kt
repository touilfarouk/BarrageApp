package com.codewithfk.travenor.storage

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.codewithfk.domain.storage.TokenStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_store")

class AndroidTokenStorage(
    private val context: Context
) : TokenStorage {

    private val tokenKey: Preferences.Key<String> = stringPreferencesKey("access_token")

    override val tokenFlow: Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[tokenKey] }

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
}
