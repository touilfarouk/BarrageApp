package com.codewithfk.presentation.di

import com.codewithfk.domain.storage.TokenStorage
import com.codewithfk.domain.usecase.ClearTokenUseCase
import com.codewithfk.domain.usecase.GetAllProgrammesUseCase
import com.codewithfk.domain.usecase.GetTokenUseCase
import com.codewithfk.domain.usecase.ObserveTokenUseCase
import com.codewithfk.domain.usecase.RegisterUseCase
import com.codewithfk.domain.usecase.SignInUseCase
import com.codewithfk.presentation.feature.auth.TokenViewModel
import com.codewithfk.presentation.feature.programmes.ProgrammesViewModel
import com.codewithfk.presentation.feature.register.SignInViewModel
import com.codewithfk.presentation.feature.signIn.RegisterViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SignInViewModel(get<SignInUseCase>(), get<TokenStorage>()) }
    viewModel { RegisterViewModel(get<RegisterUseCase>()) }
    viewModel { TokenViewModel(get<GetTokenUseCase>(), get<ClearTokenUseCase>(), get<ObserveTokenUseCase>()) }
    viewModel { ProgrammesViewModel(get<GetAllProgrammesUseCase>(), get<GetTokenUseCase>()) }
}
