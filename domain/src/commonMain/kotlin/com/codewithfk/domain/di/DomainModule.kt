package com.codewithfk.domain.di

import com.codewithfk.domain.repository.ListingRepository
import com.codewithfk.domain.repository.UserRepository
import com.codewithfk.domain.usecase.GetAllListingUseCase
import com.codewithfk.domain.usecase.RegisterUseCase
import com.codewithfk.domain.usecase.SignInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetAllListingUseCase(get<ListingRepository>())
    }
    factory {
        SignInUseCase(get<UserRepository>())
    }
    factory {
        RegisterUseCase(get<UserRepository>())
    }
}