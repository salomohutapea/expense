package dev.salomo.expense.data.repositories

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ExpensesRepositoryImpl @Inject constructor() : ExpensesRepository {
    override fun getHome() = flow {
        delay(1000)
        emit("HOME")
    }

    override fun getOther() = flow {
        delay(500)
        emit("PAGE")
    }

    override fun getGraphs() = flow {
        delay(2000)
        emit("GRAPHS")
    }
}