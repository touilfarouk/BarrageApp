package com.codewithfk.travenor.listings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codewithfk.presentation.feature.listings.TravelListingViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TravelListingScreen(viewModel: TravelListingViewModel = koinViewModel()) {
    Scaffold {
        val listingsState = viewModel.state.collectAsStateWithLifecycle()
        LazyColumn(modifier = Modifier.padding(it)) {
            items(listingsState.value.listings) { listing ->
                TravelListingItem(listing = listing)
            }
        }
    }
}

@Composable
fun TravelListingItem(listing: com.codewithfk.domain.model.TravelListing) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(text = listing.title)
        Text(text = listing.description)
    }
}