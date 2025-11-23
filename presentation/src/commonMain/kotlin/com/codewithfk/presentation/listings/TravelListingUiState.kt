package com.codewithfk.presentation.listings

import com.codewithfk.domain.model.TravelListing


data class TravelListingUiState(
    val listings:List<TravelListing> = emptyList(),
    val isLoading:Boolean = false,
    val errorMessage:String? = null
) {
    val hasListings: Boolean
        get() = listings.isNotEmpty()

    val showEmptyState: Boolean
        get() = !isLoading && !hasListings && errorMessage == null

}