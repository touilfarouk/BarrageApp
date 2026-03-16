package com.codewithfk.data.repository

import com.codewithfk.data.datasource.RemoteDataSource
import com.codewithfk.data.mappers.RegisterRequestMapper
import com.codewithfk.data.mappers.UserMapper
import com.codewithfk.data.model.request.SignInRequest
import com.codewithfk.domain.model.AuthTokenModel
import com.codewithfk.domain.model.RegisterModel
import com.codewithfk.domain.model.UserModel
import com.codewithfk.domain.repository.UserRepository

class UserRepositoryImp(val dataSource: RemoteDataSource) : UserRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<AuthTokenModel> {

        return try {
            val response = dataSource.signIn(SignInRequest(email, password))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val token = response.accessToken ?: response.token
                if (!token.isNullOrBlank()) {
                    Result.success(AuthTokenModel(accessToken = token))
                } else {
                    Result.failure(Exception("Login failed"))
                }
            } else {
                Result.failure(Exception("Login failed with status code: ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(request: RegisterModel): Result<UserModel> {
        return try {
            val response = dataSource.register(RegisterRequestMapper.toDto(request))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val userDto = response.user
                if (userDto != null) {
                    val userModel = UserMapper.toDomain(userDto)
                    Result.success(userModel)
                } else {
                    Result.failure(Exception("Registration failed"))
                }
            } else {
                Result.failure(Exception("Registration failed with status code: ${response.exceptionOrNull()}"))
            }

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
