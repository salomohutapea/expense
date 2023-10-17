package dev.salomo.expense.presentation.screens.graphs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.salomo.expense.R

@Composable
fun GraphsScreen(
    graphsScreenViewModel: GraphsScreenViewModel = hiltViewModel(), goToHome: () -> Unit
) {
    val uiState by graphsScreenViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val s = uiState) {
            is GraphsScreenUiState.Ready -> Content(goToHome, s.graphs, s.other)
            is GraphsScreenUiState.Loading -> Loading()
            is GraphsScreenUiState.Error -> Error()
        }
    }
}

@Composable
private fun Content(
    goToHome: () -> Unit, graphs: String, other: String, modifier: Modifier = Modifier
) {
    Text(text = "$graphs $other", modifier = modifier)
    Button(onClick = goToHome) {
        Text(
            text = stringResource(R.string.go_to_home),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
private fun Loading(modifier: Modifier = Modifier) {
    Text(text = "Loading...", modifier = modifier)
}

@Composable
private fun Error(modifier: Modifier = Modifier) {
    Text(text = "Something went wrong", modifier = modifier)
}