package dev.salomo.expense.data.repositories

import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {
    fun getHome(): Flow<String>
    fun getOther(): Flow<String>
    fun getGraphs(): Flow<String>
}