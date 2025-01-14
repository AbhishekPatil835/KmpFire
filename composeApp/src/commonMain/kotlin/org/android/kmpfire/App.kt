package org.android.kmpfire

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.android.kmpfire.presentation.viewmodel.MainViewModel
import org.android.kmpfire.ui.JokeListComposable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "JokesList"
            ) {
                composable("JokesList") {
                    val viewModel = koinViewModel<MainViewModel>()
                    JokeListComposable(viewModel.uiState, viewModel::onEvent)
                }
            }
        }
    }
}