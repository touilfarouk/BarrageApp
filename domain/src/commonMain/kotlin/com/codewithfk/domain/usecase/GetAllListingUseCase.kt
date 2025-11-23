package com.codewithfk.domain.usecase

import com.codewithfk.domain.model.TravelListing
import com.codewithfk.domain.repository.ListingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllListingUseCase(private val repository: ListingRepository) {
    fun execute(): Flow<List<TravelListing>> {
        val data = repository.getAllListings()
        data.map {
            it.sortedByDescending { listing ->
                listing.rating
            }
        }
        return data
    }
}