package com.example.s215824_lykkehjulet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.s215824_lykkehjulet.ui.screens.game.GameViewModel
import com.example.s215824_lykkehjulet.ui.screens.menu.MenuScreen
import com.example.s215824_lykkehjulet.ui.screens.rules.RulesScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameLostScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameWonScreen

/**
 * This file contains a navigation graph. You are able to navigate arguments between the different
 * screens. Also, i decided to implement the viewModel here, since the GameWon and GameLost Screens
 * both use a method from the ViewModel that allows the player to play again.
 *
 * Here a better solution would be to separate the business logic away from the ViewModel in
 * another package called model. Inside here, we could create a class called PlayAgainUseCase.
 * But I won't do that for now. Maybe later :p
 */
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

        composable(
            route = Screen.GameLostScreen.route,
            arguments = listOf(
                navArgument(POINTS) {
                    type = NavType.IntType
                },
                navArgument(WORD) {
                    type = NavType.StringType
                },
                navArgument(CATEGORY) {
                    type = NavType.StringType
                },
                navArgument(ATTEMPTS) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
                GameLostScreen(
                    navController = navController,
                    gameViewModel = gameViewModel,
                    point = backStackEntry.arguments?.getInt(POINTS)!!,
                    word = backStackEntry.arguments?.getString(WORD).toString(),
                    category = backStackEntry.arguments?.getString(CATEGORY).toString(),
                    attempts = backStackEntry.arguments?.getInt(ATTEMPTS)!!
                )
            }

        composable(
            route = Screen.GameWonScreen.route,
            arguments = listOf(
                navArgument(POINTS) {
                    type = NavType.IntType
                },
                navArgument(LIVES) {
                    type = NavType.IntType
                },
                navArgument(WORD) {
                    type = NavType.StringType
                },
                navArgument(CATEGORY) {
                    type = NavType.StringType
                },
                navArgument(ATTEMPTS) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            GameWonScreen(
                navController = navController,
                gameViewModel = gameViewModel,
                point = backStackEntry.arguments?.getInt(POINTS)!!,
                lives = backStackEntry.arguments?.getInt(LIVES)!!,
                word = backStackEntry.arguments?.getString(WORD).toString(),
                category = backStackEntry.arguments?.getString(CATEGORY).toString(),
                attempts = backStackEntry.arguments?.getInt(ATTEMPTS)!!
            )
        }
    }
}