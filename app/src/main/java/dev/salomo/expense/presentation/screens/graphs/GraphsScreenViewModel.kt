package dev.salomo.expense.presentation.screens.graphs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.salomo.expense.data.repositories.ExpensesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GraphsScreenViewModel @Inject constructor(
    expensesRepository: ExpensesRepository
) : ViewModel() {
    val uiState: StateFlow<GraphsScreenUiState> = combine(
        expensesRepository.getGraphs(), expensesRepository.getOther(),
    ) { graphs, other ->
        GraphsScreenUiState.Ready(graphs, other)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = GraphsScreenUiState.Loading
    )
}

sealed interface GraphsScreenUiState {
    object Loading : GraphsScreenUiState
    object Error : GraphsScreenUiState
    data class Ready(
        val graphs: String, val other: String
    ) : GraphsScreenUiState

}