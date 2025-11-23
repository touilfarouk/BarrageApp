package com.codewithfk.domain.repository

import com.codewithfk.domain.model.TravelListing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    fun getAllListings(): Flow<List<TravelListing>>
    fun getListingById(id: String): Flow<TravelListing?>
}