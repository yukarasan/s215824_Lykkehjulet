package com.example.s215824_lykkehjulet.model

/**
 * Data class that represents the game UI state
 */
data class UiState(
    var category: String = "",
    val currentWord: String = "",
    val wordLength: Int = 0,
    val point: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)