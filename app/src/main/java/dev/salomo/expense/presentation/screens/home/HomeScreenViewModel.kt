package dev.salomo.expense.presentation.screens.home

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
class HomeScreenViewModel @Inject constructor(
    expensesRepository: ExpensesRepository
) : ViewModel() {
    val uiState: StateFlow<HomeScreenUiState> = combine(
        expensesRepository.getHome(), expensesRepository.getOther(),
    ) { home, other ->
        HomeScreenUiState.Ready(home, other)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeScreenUiState.Loading
    )
}

sealed interface HomeScreenUiState {
    object Loading : HomeScreenUiState
    object Error : HomeScreenUiState
    data class Ready(
        val home: String, val other: String
    ) : HomeScreenUiState

}