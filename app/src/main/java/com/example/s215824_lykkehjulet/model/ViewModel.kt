package com.example.s215824_lykkehjulet.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModel: ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    /*
     * usedWords keeps track of all the words that have been used so far in the game.
     * This is needed because a user should not have to guess the same word twice.
     */
    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    // Keeps track of the current category that have been randomly chosen.
    private lateinit var currentCategory: String

    init {
        resetGame()
    }

    /*
     * Re-initializes all of the data in our game the values defined in the UiState data class.
     * This will give an effect of restarting a game.
     */
    private fun resetGame() {
        pickRandomCategory()
    }

    private fun pickRandomCategory() {
        // Creates a random number between 1 and 3, to choose between the 3 different categories
        val randomCategory = (1..3).random()

        // Sets the current category equal to the chosen category.
        when (randomCategory) {
            1 -> currentCategory = "animals"
            2 -> currentCategory = "cars"
            3 -> currentCategory = "jobTitle"
        }
        _uiState.value.category = currentCategory
    }

    private fun pickRandomWordFromCategory() {

    }

    private fun hideRandomWord() {

    }

    private fun updateState() {

    }
}