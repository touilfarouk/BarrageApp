package com.codewithfk.travenor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codewithfk.data.datasource.DummyDataSource
import com.codewithfk.data.repository.ListingRepositoryImpl
import com.codewithfk.domain.usecase.GetAllListingUseCase
import com.codewithfk.presentation.listings.TravelListingViewModel
import com.codewithfk.travenor.listings.TravelListingScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import travenorcmp.composeapp.generated.resources.Res
import travenorcmp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        TravelListingScreen()
    }
}

