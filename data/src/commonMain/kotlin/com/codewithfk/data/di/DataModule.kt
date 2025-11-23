package com.codewithfk.data.di

import com.codewithfk.data.datasource.DummyDataSource
import com.codewithfk.data.repository.ListingRepositoryImpl
import com.codewithfk.domain.repository.ListingRepository
import org.koin.dsl.module

val dataModule = module {
    single { DummyDataSource() }

    single<ListingRepository> {
        ListingRepositoryImpl(
            get<DummyDataSource>()
        )
    }
}