package com.example.s215824_lykkehjulet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.s215824_lykkehjulet.model.UiState
import com.example.s215824_lykkehjulet.ui.screens.MenuScreen
import com.example.s215824_lykkehjulet.ui.RulesScreen
import com.example.s215824_lykkehjulet.ui.screens.game.GameScreen

@Composable
fun SetupNavGraph(navController: NavHostController, uiState: UiState) {
    NavHost(navController = navController, startDestination = Screen.MenuScreen.route) {
        composable(route = Screen.MenuScreen.route) {
            MenuScreen(navController = navController)
        }
        composable(route = Screen.RulesScreen.route) {
            RulesScreen(navController = navController)
        }
        composable(route = Screen.GameScreen.route) {
            GameScreen(navController = navController, uiState = uiState)
        }
    }
}