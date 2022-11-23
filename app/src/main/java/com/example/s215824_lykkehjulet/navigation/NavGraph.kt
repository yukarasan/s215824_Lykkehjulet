package com.example.s215824_lykkehjulet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.s215824_lykkehjulet.ui.screens.game.viewModel.GameViewModel
import com.example.s215824_lykkehjulet.ui.screens.menu.MenuScreen
import com.example.s215824_lykkehjulet.ui.screens.rules.RulesScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameLostScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameWonScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Screen.MenuScreen.route) {
        composable(route = Screen.MenuScreen.route) {
            MenuScreen(navController = navController)
        }
        composable(route = Screen.RulesScreen.route) {
            RulesScreen(navController = navController)
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(
                navController = navController,
                gameUiState = gameUiState,
                gameViewModel = gameViewModel
            )
        }
        composable(route = Screen.GameLostScreen.route) {
            GameLostScreen(
                navController = navController,
                gameUiState = gameUiState,
                gameViewModel = gameViewModel
            )
        }
        composable(route = Screen.GameWonScreen.route) {
            GameWonScreen(
                navController = navController,
                gameUiState = gameUiState,
                gameViewModel = gameViewModel
            )
        }
    }
}