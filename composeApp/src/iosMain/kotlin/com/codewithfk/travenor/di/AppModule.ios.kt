package com.codewithfk.travenor.di

import com.codewithfk.domain.storage.TokenStorage
import com.codewithfk.travenor.storage.IosTokenStorage
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<String> { "http://213.179.181.50/api" }
    single<TokenStorage> { IosTokenStorage() }
}
