package com.example.s215824_lykkehjulet.model

/**
 * Data class that represents the game UI state
 */
data class GameUiState(
    var category: String = "",
    var currentWord: String = "",
    var wordLength: Int = 0,
    var point: Int = 0,
    var lives: Int = 5,
    var assignedPoint: Int = 0,
    var haveUserSpunWheel: Boolean = false,
    var haveUserGuessed: Boolean = false,
    var isGuessedWordCorrect: Boolean = false,
    var guessedCharacter: String = "",
    var isButtonClicked: Boolean = false,
    var numOfCorrectGuesses: Int = 0,
    var numOfTotalGuesses: Int = 0,
    var numOfMultiplication: Int = 0
)

// TODO: Change to GameUiState and move to Screen package.