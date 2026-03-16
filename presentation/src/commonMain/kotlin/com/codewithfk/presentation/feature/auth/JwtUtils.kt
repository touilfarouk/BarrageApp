package com.codewithfk.presentation.feature.auth

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object JwtUtils {
    private val json = Json { ignoreUnknownKeys = true }

    fun extractRoles(token: String): String? {
        val payload = decodeJwtPayload(token) ?: return null
        val root = json.parseToJsonElement(payload).jsonObject
        val userInfo = root["UserInfo"]?.jsonObject ?: return null
        return userInfo["roles"]?.jsonPrimitive?.content
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun decodeJwtPayload(token: String): String? {
        val parts = token.split(".")
        if (parts.size < 2) return null
        val payload = parts[1]
        val padded = payload.padEnd(((payload.length + 3) / 4) * 4, '=')
        return try {
            Base64.UrlSafe.decode(padded).decodeToString()
        } catch (_: IllegalArgumentException) {
            null
        }
    }
}
