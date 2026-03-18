package com.codewithfk.data.datasource

import com.codewithfk.data.model.ProgrammeDto
import com.codewithfk.data.model.SignInResponse
import com.codewithfk.data.model.request.RegisterRequest
import com.codewithfk.data.model.request.SignInRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import com.codewithfk.domain.error.AuthExpiredException
import com.codewithfk.domain.error.UnexpectedResponseException

class RemoteDataSource(private val httpClient: HttpClient, private val baseUrl: String) {
    private val BASE_URL = baseUrl
    private val SIGN_IN_ENDPOINT = "${BASE_URL}/auth/login"
    private val REGISTER_ENDPOINT = "${BASE_URL}/auth/register"
    private val PROGRAMMES_ENDPOINT = "${BASE_URL}/allprogrammes"

    suspend fun signIn(request: SignInRequest): Result<SignInResponse> {
        return try {
            val response = httpClient.post(urlString = SIGN_IN_ENDPOINT) {
                setBody(request)
            }
            Result.success(response.body())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
    suspend fun register(request: RegisterRequest): Result<SignInResponse> {
        return try {
            val response = httpClient.post(urlString = REGISTER_ENDPOINT) {
                setBody(request)
            }
            Result.success(response.body())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }

    suspend fun getProgrammes(token: String): Result<List<ProgrammeDto>> {
        return try {
            val response = httpClient.post(urlString = PROGRAMMES_ENDPOINT) {
                header(HttpHeaders.Authorization, "Bearer $token")
            }
            var status = response.status
            if (status == HttpStatusCode.Found) {
                val location = response.headers[HttpHeaders.Location]
                if (!location.isNullOrBlank() && location.startsWith("https://")) {
                    val retryResponse = httpClient.post(urlString = location) {
                        header(HttpHeaders.Authorization, "Bearer $token")
                    }
                    status = retryResponse.status
                    if (!status.isSuccess()) {
                        return Result.failure(
                            UnexpectedResponseException(
                                statusCode = status.value,
                                contentType = retryResponse.contentType()?.toString(),
                                body = retryResponse.bodyAsText(),
                                location = location
                            )
                        )
                    }
                    val contentType = retryResponse.contentType()
                    if (contentType == null || !contentType.match(ContentType.Application.Json)) {
                        return Result.failure(
                            UnexpectedResponseException(
                                statusCode = status.value,
                                contentType = contentType?.toString(),
                                body = retryResponse.bodyAsText(),
                                location = location
                            )
                        )
                    }
                    return Result.success(retryResponse.body())
                } else {
                    return Result.failure(
                        UnexpectedResponseException(
                            statusCode = status.value,
                            contentType = response.contentType()?.toString(),
                            body = response.bodyAsText(),
                            location = location
                        )
                    )
                }
            }
            if (status == HttpStatusCode.Found ||
                status == HttpStatusCode.Unauthorized ||
                status == HttpStatusCode.Forbidden
            ) {
                return Result.failure(AuthExpiredException("Session expired. Please sign in again."))
            }
            if (!status.isSuccess()) {
                return Result.failure(
                    UnexpectedResponseException(
                        statusCode = status.value,
                        contentType = response.contentType()?.toString(),
                        body = response.bodyAsText()
                    )
                )
            }
            val contentType = response.contentType()
            if (contentType == null || !contentType.match(ContentType.Application.Json)) {
                return Result.failure(
                    UnexpectedResponseException(
                        statusCode = status.value,
                        contentType = contentType?.toString(),
                        body = response.bodyAsText()
                    )
                )
            }
            Result.success(response.body())
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
