package com.codewithfk.domain.di

import com.codewithfk.domain.repository.ListingRepository
import com.codewithfk.domain.usecase.GetAllListingUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetAllListingUseCase(get<ListingRepository>())
    }
}