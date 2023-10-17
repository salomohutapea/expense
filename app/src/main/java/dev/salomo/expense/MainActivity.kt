package dev.salomo.expense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.salomo.expense.presentation.screens.Screens
import dev.salomo.expense.presentation.screens.graphs.GraphsScreen
import dev.salomo.expense.presentation.screens.home.HomeScreen
import dev.salomo.expense.presentation.theme.ExpenseAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    ExpenseAppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
        ) {
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onSurface
            ) {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = Screens.Home()) {
                    composable(route = Screens.Home()) {
                        HomeScreen {
                            navController.navigate(Screens.Graphs())
                        }
                    }
                    composable(route = Screens.Graphs()) {
                        GraphsScreen {
                            navController.navigate(Screens.Home())
                        }
                    }
                }
            }
        }
    }
}