package com.example.s215824_lykkehjulet.ui.screens.game

import androidx.lifecycle.ViewModel
import com.example.s215824_lykkehjulet.data.Category
import com.example.s215824_lykkehjulet.data.Characters
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel() {
    // UI state
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    // Keeps track of the current word and its length
    private lateinit var currentWord: String
    private var wordLength: Int = 0

    // Keeps track of the current category that have been randomly chosen.
    private lateinit var currentCategory: String

    /*
     * Keeps track of how many correct guessed there have been, and thereby which letters to
     * display on the screen
     */
    private lateinit var correctGuessedCharacter: String

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

    fun playAgain() {
        resetGame()
    }

    /*
     * Since this game don't have much data, I have decided to hardcode the categories here.
     * In the future, this would need to be done differently.
     */
    private fun pickRandomCategory(): String {
        // Creates a random number between 1 and 7, to choose between the 7 different categories
        when ((1..7).shuffled().last()) {
            1 -> currentCategory = "Dyr"
            2 -> currentCategory = "Biler"
            3 -> currentCategory = "Job titler"
            4 -> currentCategory = "Tøj"
            5 -> currentCategory = "Danske byer"
            6 -> currentCategory = "Lande"
            7 -> currentCategory = "Verdens byer"
        }

        return currentCategory
    }

    private fun pickRandomWordFromCategory(): String {
        val category = Category()

        when (currentCategory) {
            "Dyr" -> {
                // Get word from animals
                currentWord = category.animals().shuffled().last()
                // Remove word from category
                category.animals().remove(currentWord)
            }
            "Biler" -> {
                // Get word from cars
                currentWord = category.cars().shuffled().last()
                // Remove word from category
                category.cars().remove(currentWord)
            }
            "Job titler" -> {
                // Get word from job titles
                currentWord = category.jobTitle().shuffled().last()
                // Remove word from category
                category.jobTitle().remove(currentWord)
            }
            "Tøj" -> {
                // Get word from clothes
                currentWord = category.clothes().shuffled().last()
                // Remove word from category
                category.clothes().remove(currentWord)
            }
            "Danske byer" -> {
                // Get word from cities
                currentWord = category.cities().shuffled().last()
                // Remove word from category
                category.cities().remove(currentWord)
            }
            "Verdens lande" -> {
                // Get word from countries
                currentWord = category.countries().shuffled().last()
                // Remove word from category
                category.countries().remove(currentWord)
            }
            "Verdens byer" -> {
                // Get word from world cities
                currentWord = category.worldCities().shuffled().last()
                // Remove word from category
                category.worldCities().remove(currentWord)
            }
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

    fun getDanishCharacters(row: Int): List<Char> {
        val characters = Characters()

        // Creating sub lists for each row to create the rows of clickable characters
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
        when ((1..20).shuffled().last()) {
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
        _uiState.value.isBankrupt = false


        if (_uiState.value.assignedPoint == 0) {
            // If player goes bankrupt, give the option to spin the wheel again:
            _uiState.value.isBankrupt = true
            _uiState.value.haveUserSpunWheel = false

            _uiState.value.point = 0
        }
    }

    fun checkUserGuess(guessedCharacter: String) {
        uiState.value.guessedCharacter = guessedCharacter
        _uiState.value.haveUserSpunWheel = false
        _uiState.value.haveUserGuessed = true
        _uiState.value.numOfTotalGuesses++

        if (currentWord.contains(uiState.value.guessedCharacter)) {
            _uiState.value.isGuessedWordCorrect = true
            // Setting the number of multiplication back to 0 for next round:
            _uiState.value.numOfMultiplication = 0

            correctGuessedCharacter = uiState.value.guessedCharacter

            // Used to make a list of guessed characters that should be displayed on the word.
            _uiState.value.listOfGuessedCharacters =
                uiState.value.listOfGuessedCharacters + correctGuessedCharacter

            /*
            This for-loop checks for all the char values in currentWord, if they are equal to
            the guessed character. For each occurrence, number of correct guesses increments.
             */
            for (n in _uiState.value.currentWord) {
                if (n.toString() == uiState.value.guessedCharacter) {
                    // Used to keep track of when the game should end.
                    _uiState.value.numOfCorrectGuesses++
                    // Used to multiply with the number assigned point.
                    _uiState.value.numOfMultiplication++
                }
            }

            // Assigning the point
            _uiState.value.point = _uiState.value.point +
                    (uiState.value.assignedPoint * _uiState.value.numOfMultiplication)
        } else {
            _uiState.value.isGuessedWordCorrect = false
            _uiState.value.lives--
        }
    }
}