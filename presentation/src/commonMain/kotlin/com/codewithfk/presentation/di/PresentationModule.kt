package com.codewithfk.presentation.di

import com.codewithfk.domain.usecase.GetAllListingUseCase
import com.codewithfk.presentation.listings.TravelListingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
}