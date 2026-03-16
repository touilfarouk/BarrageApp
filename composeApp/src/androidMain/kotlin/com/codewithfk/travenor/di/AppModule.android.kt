package com.codewithfk.travenor.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module  = module {
    single<String> { "http://213.179.181.50/api" }
}
