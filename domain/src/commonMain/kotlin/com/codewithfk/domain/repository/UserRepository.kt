package com.codewithfk.domain.repository

import com.codewithfk.domain.model.RegisterModel
import com.codewithfk.domain.model.AuthTokenModel
import com.codewithfk.domain.model.UserModel

interface UserRepository {
    suspend fun login(email: String, password: String): Result<AuthTokenModel>
    suspend fun register(request: RegisterModel): Result<UserModel>
}
