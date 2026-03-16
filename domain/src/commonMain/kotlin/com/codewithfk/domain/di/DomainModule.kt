package com.codewithfk.domain.di

import com.codewithfk.domain.repository.ProgrammeRepository
import com.codewithfk.domain.repository.UserRepository
import com.codewithfk.domain.storage.TokenStorage
import com.codewithfk.domain.usecase.ClearTokenUseCase
import com.codewithfk.domain.usecase.GetAllProgrammesUseCase
import com.codewithfk.domain.usecase.GetTokenUseCase
import com.codewithfk.domain.usecase.ObserveTokenUseCase
import com.codewithfk.domain.usecase.RegisterUseCase
import com.codewithfk.domain.usecase.SignInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetAllProgrammesUseCase(get<ProgrammeRepository>())
    }
    factory {
        SignInUseCase(get<UserRepository>())
    }
    factory {
        RegisterUseCase(get<UserRepository>())
    }
    factory {
        GetTokenUseCase(get<TokenStorage>())
    }
    factory {
        ClearTokenUseCase(get<TokenStorage>())
    }
    factory {
        ObserveTokenUseCase(get<TokenStorage>())
    }
}
