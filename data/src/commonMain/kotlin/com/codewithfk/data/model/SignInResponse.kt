package com.codewithfk.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean? = null,
    val accessToken: String? = null,
    val token: String? = null,
    val user: UserDto? = null
)
