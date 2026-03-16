package com.codewithfk.travenor.programmes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.codewithfk.domain.model.Programme
import com.codewithfk.presentation.feature.auth.TokenViewModel
import com.codewithfk.presentation.feature.programmes.ProgrammesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProgrammesScreen(
    viewModel: ProgrammesViewModel = koinViewModel(),
    tokenViewModel: TokenViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val tokenState = tokenViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold { padding ->
        when {
            state.value.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(24.dp))
            }
            state.value.errorMessage != null -> {
                Text(
                    text = state.value.errorMessage.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.padding(padding)) {
                    item {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Role: ${tokenState.value.roles ?: "-"}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Row(modifier = Modifier.padding(top = 8.dp)) {
                                Button(onClick = { tokenViewModel.logout() }) {
                                    Text("Log Out")
                                }
                            }
                        }
                    }
                    items(state.value.programmes) { programme ->
                        ProgrammeItem(programme = programme)
                    }
                }
            }
        }
    }
}

@Composable
fun ProgrammeItem(programme: Programme) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = programme.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "Start: ${programme.startDate ?: "-"}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "End: ${programme.endDate ?: "-"}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Type: ${programme.typeMarche ?: "-"}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
