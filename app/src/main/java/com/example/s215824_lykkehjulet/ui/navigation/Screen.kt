package com.example.s215824_lykkehjulet.ui.navigation

const val POINTS = "points"
const val LIVES = "lives"
const val WORD = "word"
const val CATEGORY = "category"
const val ATTEMPTS = "attempts"

sealed class Screen(val route: String) {
    object MenuScreen : Screen(route = "menu")
    object RulesScreen : Screen(route = "rules")
    object GameScreen : Screen(route = "game")

    object GameLostScreen :
        Screen(route = "gameLostScreen/{$POINTS}/{$WORD}/{$CATEGORY}/{$ATTEMPTS}") {
        fun passArguments(
            point: Int,
            word: String,
            category: String,
            attempts: Int
        ): String {
            return "gameLostScreen/$point/$word/$category/$attempts"
        }
    }

    object GameWonScreen :
        Screen(route = "gameWonScreen/{$POINTS}/{$LIVES}/{$WORD}/{$CATEGORY}/{$ATTEMPTS}") {
        fun passArguments(
            point: Int,
            lives: Int,
            word: String,
            category: String,
            attempts: Int
        ): String {
            return "gameWonScreen/$point/$lives/$word/$category/$attempts"
        }
    }
}
