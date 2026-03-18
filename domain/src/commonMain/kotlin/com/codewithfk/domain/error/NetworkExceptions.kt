package com.codewithfk.domain.error

class AuthExpiredException(message: String = "Session expired.") : Exception(message)

class UnexpectedResponseException(
    val statusCode: Int,
    val contentType: String?,
    val body: String?,
    val location: String? = null
) : Exception("Unexpected response: HTTP $statusCode, contentType=$contentType, location=$location")
