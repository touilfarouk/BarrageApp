package com.codewithfk.domain.usecase

import com.codewithfk.domain.model.RegisterModel
import com.codewithfk.domain.model.UserModel
import com.codewithfk.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend fun execute(request: RegisterModel): Result<UserModel> {
        return repository.register(request)
    }
}
