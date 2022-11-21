package com.example.s215824_lykkehjulet.navigation

sealed class Screen(val route: String) {
    object MenuScreen: Screen(route = "menu")
    object RulesScreen: Screen(route = "rules")
    object GameScreen: Screen(route = "game")
    object GameLostScreen: Screen(route = "gameLost")
    object GameWonScreen: Screen(route = "gameWonScreen")
}
