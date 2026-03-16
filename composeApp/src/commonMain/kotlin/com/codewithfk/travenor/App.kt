package com.codewithfk.travenor

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.codewithfk.presentation.feature.auth.TokenViewModel
import com.codewithfk.travenor.programmes.ProgrammesScreen
import com.codewithfk.travenor.ui.signin.LoginScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val tokenViewModel: TokenViewModel = koinViewModel()
        val tokenState = tokenViewModel.uiState.collectAsState()

        when {
            tokenState.value.isLoading -> {
                CircularProgressIndicator(modifier = Modifier)
            }
            tokenState.value.token != null -> {
                ProgrammesScreen()
            }
            else -> {
                LoginScreen()
            }
        }

    }
}

