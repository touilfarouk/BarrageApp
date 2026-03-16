package com.codewithfk.domain.usecase

import com.codewithfk.domain.model.AuthTokenModel
import com.codewithfk.domain.repository.UserRepository

class SignInUseCase(private val repository: UserRepository) {
    suspend fun execute(userName: String, password: String): Result<AuthTokenModel> {
        return repository.login(userName, password)
    }
}
