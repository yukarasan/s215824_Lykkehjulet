package com.example.s215824_lykkehjulet.model.navigation

sealed class Screen(val route: String) {
    object MenuScreen: Screen(route = "menu")
    object RulesScreen: Screen(route = "rules")
}
