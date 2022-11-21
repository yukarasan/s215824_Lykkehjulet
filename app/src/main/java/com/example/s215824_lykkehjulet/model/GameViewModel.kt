package com.example.s215824_lykkehjulet.model

import android.graphics.drawable.Animatable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.s215824_lykkehjulet.data.Category
import com.example.s215824_lykkehjulet.data.Characters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Random
import kotlin.random.Random.Default.nextInt

class GameViewModel :
    ViewModel() {      // TODO: Change to GameViewModel and move to Screen package.
    // UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    // Keeps track of the current word and its length
    private lateinit var currentWord: String
    var wordLength: Int = 0

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
        _uiState.value = GameUiState(
            category = pickRandomCategory(),
            currentWord = pickRandomWordFromCategory(),
            point = 0,
            lives = 5
        )
        setCurrentWordAndFindLength()
    }

    /*
     * Since this game don't have much data, I have decided to hardcode the categories here.
     * In the future, this would need to be done differently.
     */
    private fun pickRandomCategory(): String {
        // Creates a random number between 1 and 5, to choose between the 5 different categories
        val randomCategory = (1..5).shuffled().last()

        when (randomCategory) {
            1 -> currentCategory = "Dyr"
            2 -> currentCategory = "Biler"
            3 -> currentCategory = "Job titler"
            4 -> currentCategory = "Tøj"
            5 -> currentCategory = "Byer"
        }

        return currentCategory
    }

    private fun pickRandomWordFromCategory(): String {
        val category = Category()

        // TODO: Once the player has won, make sure that the player wont get the same word from category.
        // if (category.animals().isEmpty())

        if (currentCategory == "Dyr") {
            // Get word from animals
            currentWord = category.animals().shuffled().last()
            // Remove word from category
            category.animals().remove(currentWord)
        } else if (currentCategory == "Biler") {
            // Get word from cars
            currentWord = category.cars().shuffled().last()
            // Remove word from category
            category.cars().remove(currentWord)
        } else if (currentCategory == "Job titler") {
            // Get word from job titles
            currentWord = category.jobTitle().shuffled().last()
            // Remove word from category
            category.jobTitle().remove(currentWord)
        } else if (currentCategory == "Tøj") {
            // Get word from clothes
            currentWord = category.clothes().shuffled().last()
            // Remove word from category
            category.clothes().remove(currentWord)
        } else if (currentCategory == "Byer") {
            // Get word from cities
            currentWord = category.cities().shuffled().last()
            // Remove word from category
            category.cities().remove(currentWord)
        }
        return currentWord
    }

    private fun setCurrentWordAndFindLength() {
        // Sets the current word:
        _uiState.value.currentWord = currentWord

        // Finds the word length:
        wordLength = currentWord.length
        _uiState.value.wordLength = wordLength
    }

    private fun hideWord() {

    }

    private fun unHideLetter() {

    }

    fun getDanishCharacters(row: Int): List<Char> {
        val characters = Characters()

        // Creating sub lists for each row:
        val firstRow = characters.loadDanishCharacters().subList(0, 7)
        val secondRow = characters.loadDanishCharacters().subList(7, 14)
        val thirdRow = characters.loadDanishCharacters().subList(14, 21)
        val fourthRow = characters.loadDanishCharacters().subList(21, 28)
        val fifthRow = characters.loadDanishCharacters().subList(28, 29)

        if (row == 1) return firstRow
        if (row == 2) return secondRow
        if (row == 3) return thirdRow
        if (row == 4) return fourthRow
        if (row == 5) return fifthRow

        // If row has not been specified, then just return all characters
        return firstRow + secondRow + thirdRow + fourthRow
    }

    fun pickPointFromWheel() {
        // Creates a random number between 1 and 20, to choose between the different points
        val randomNumber = (1..20).shuffled().last()

        when (randomNumber) {
            1 -> _uiState.value.assignedPoint = 0
            2 -> _uiState.value.assignedPoint = 100
            3 -> _uiState.value.assignedPoint = 100
            4 -> _uiState.value.assignedPoint = 300
            5 -> _uiState.value.assignedPoint = 500
            6 -> _uiState.value.assignedPoint = 500
            7 -> _uiState.value.assignedPoint = 500
            8 -> _uiState.value.assignedPoint = 500
            9 -> _uiState.value.assignedPoint = 500
            10 -> _uiState.value.assignedPoint = 500
            11 -> _uiState.value.assignedPoint = 600
            12 -> _uiState.value.assignedPoint = 600
            13 -> _uiState.value.assignedPoint = 800
            14 -> _uiState.value.assignedPoint = 800
            15 -> _uiState.value.assignedPoint = 800
            16 -> _uiState.value.assignedPoint = 800
            17 -> _uiState.value.assignedPoint = 800
            18 -> _uiState.value.assignedPoint = 1000
            19 -> _uiState.value.assignedPoint = 1000
            20 -> _uiState.value.assignedPoint = 1500
        }

        _uiState.value.haveUserSpunWheel = true
    }

    // TODO: If user guess is correct, then make the letter in the word no longer hidden.

    fun checkUserGuess(guessedCharacter: String) {
        uiState.value.guessedCharacter = guessedCharacter
        _uiState.value.haveUserSpunWheel = false
        _uiState.value.haveUserGuessed = true

        if (currentWord.contains(uiState.value.guessedCharacter)) {
            _uiState.value.isGuessedWordCorrect = true

            if (_uiState.value.assignedPoint == 0) {
                _uiState.value.point = 0
            } else {
                _uiState.value.point = _uiState.value.point + (uiState.value.assignedPoint) // TODO: Multiply with number of occurrences
            }

        } else {
            _uiState.value.isGuessedWordCorrect = false
            _uiState.value.lives--
        }
    }

    fun playAgain() {
        resetGame()
    }
}