package com.codewithfk.travenor.di

import com.codewithfk.data.di.dataModule
import com.codewithfk.domain.di.domainModule
import com.codewithfk.presentation.di.presentationModule

val appModule = listOf(
    presentationModule, domainModule, dataModule
)