package dev.salomo.expense.presentation.screens.home

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
fun HomeScreen(
    homeScreeViewModel: HomeScreenViewModel = hiltViewModel(), goToGraphs: () -> Unit
) {
    val uiState by homeScreeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val s = uiState) {
            is HomeScreenUiState.Ready -> Content(goToGraphs, s.home, s.other)
            is HomeScreenUiState.Loading -> Loading()
            is HomeScreenUiState.Error -> Error()
        }
    }
}

@Composable
private fun Content(
    goToGraphs: () -> Unit, home: String, other: String, modifier: Modifier = Modifier
) {
    Text(text = "$home $other", modifier = modifier)
    Button(onClick = goToGraphs) {
        Text(
            text = stringResource(R.string.go_to_graphs),
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