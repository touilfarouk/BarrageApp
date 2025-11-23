package com.codewithfk.data.repository

import com.codewithfk.data.datasource.DummyDataSource
import com.codewithfk.data.mappers.TravelListingMapper
import com.codewithfk.domain.model.TravelListing
import com.codewithfk.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class ListingRepositoryImpl(val dataSource: DummyDataSource) : ListingRepository {

    private val _listings = MutableStateFlow<List<TravelListing>>(emptyList())
    val listings: StateFlow<List<TravelListing>> = _listings.asStateFlow()

    override fun getAllListings(): Flow<List<TravelListing>> {
        return dataSource.listings.map {
            val domtainModels = TravelListingMapper.toDomain(it)
            _listings.value = domtainModels
            listings.value
        }
    }

    override fun getListingById(id: String): Flow<TravelListing?> {
        return listings.map { list ->
            list.find { it.id == id }
        }
    }
}