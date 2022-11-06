package com.example.s215824_lykkehjulet.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

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
        // Creates a random number between 1 and 5, to choose between the 5 different categories
        val randomCategory = (1..5).shuffled().last()

        when (randomCategory) {
            1 -> currentCategory = "Dyr"
            2 -> currentCategory = "Biler"
            3 -> currentCategory = "Job titler"
            4 -> currentCategory = "Tøj"
            5 -> currentCategory = "Byer"
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