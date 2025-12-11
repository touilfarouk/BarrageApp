package com.codewithfk.travenor.di

import com.codewithfk.data.di.dataModule
import com.codewithfk.domain.di.domainModule
import com.codewithfk.presentation.di.presentationModule
import org.koin.dsl.module

val appModule = listOf(
    platformModule(), presentationModule, domainModule, dataModule
)

expect fun platformModule(): org.koin.core.module.Module