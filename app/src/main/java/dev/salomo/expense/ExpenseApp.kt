package dev.salomo.expense

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import dev.salomo.expense.data.repositories.ExpensesRepository
import dev.salomo.expense.data.repositories.ExpensesRepositoryImpl

@HiltAndroidApp
class ExpenseApp: Application()

@InstallIn(SingletonComponent::class)
@Module
object ExpensesRepositoryModule {

    @Provides
    fun provideExpensesRepository(): ExpensesRepository {
        return ExpensesRepositoryImpl()
    }

}