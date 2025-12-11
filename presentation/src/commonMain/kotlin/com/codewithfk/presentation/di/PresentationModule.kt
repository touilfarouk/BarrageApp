package com.codewithfk.presentation.di

import com.codewithfk.domain.usecase.GetAllListingUseCase
import com.codewithfk.domain.usecase.RegisterUseCase
import com.codewithfk.domain.usecase.SignInUseCase
import com.codewithfk.presentation.feature.listings.TravelListingViewModel
import com.codewithfk.presentation.feature.register.SignInViewModel
import com.codewithfk.presentation.feature.signIn.RegisterUiState
import com.codewithfk.presentation.feature.signIn.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TravelListingViewModel(get<GetAllListingUseCase>()) }
    viewModel { SignInViewModel(get<SignInUseCase>()) }
    viewModel { RegisterViewModel(get<RegisterUseCase>()) }
}