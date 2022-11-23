package com.example.s215824_lykkehjulet.ui.screens.game

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.navigation.Screen
import com.example.s215824_lykkehjulet.model.GameUiState
import com.example.s215824_lykkehjulet.ui.screens.game.viewModel.GameViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun GameScreen(
    navController: NavController,
    gameUiState: GameUiState,
    gameViewModel: GameViewModel
) {

    /**
     * I could not make the wheel spin work without declaring the states here.
     * Therefore I've decided, even though it is not best practice, to keep them here.
     * But since since the wheel is not a part of the requirements for this assignment, I hope
     * it won't count as a bad thing.
     */
    var wheelSpunState by remember { mutableStateOf(false) }

    val spun = mutableStateOf(false)
    val isSpun by remember { mutableStateOf(spun) }

    val guess = mutableStateOf(false)
    val isGuessed by remember { mutableStateOf(guess) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(90, 49, 160))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
        ) {
            // When player have no more lives, they'll be navigated to the lost screen.
            if (gameUiState.lives == 0) {
                LaunchedEffect(Unit) {
                    navController.navigate(route = Screen.GameLostScreen.route)
                }
            }

            if (gameUiState.numOfCorrectGuesses == gameUiState.wordLength) {
                LaunchedEffect(Unit) {
                    navController.navigate(route = Screen.GameWonScreen.route)
                }
            }

            TopBarDescription(
                category = gameUiState.category,
                lives = gameUiState.lives,
                points = gameUiState.point
            )

            Word(wordLength = gameUiState.wordLength, uiState = gameUiState)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            WheelRotateAnimation(isSpun = wheelSpunState)

            if (!isSpun.value) {
                // First spin button when the user has not made a spun yet:
                if (!gameUiState.haveUserSpunWheel) {
                    WheelSpunButton(
                        turnOrStopMessage = false,  // If false, then ask user to "Drej hjulet"
                        onClick = {
                            wheelSpunState = true
                            isSpun.value = true
                        },
                        enabled = true  // Used to enable the button.
                    )
                } else {
                    if (!isGuessed.value) {
                        WheelSpunButton(
                            turnOrStopMessage = false,  // "Drej hjulet"
                            onClick = {
                                wheelSpunState = true
                                isSpun.value = true
                            },
                            enabled = false
                        )
                    } else {
                        WheelSpunButton(
                            turnOrStopMessage = false,  // "Drej hjulet"
                            onClick = {
                                wheelSpunState = true
                                isSpun.value = true
                            },
                            enabled = false  // Used to enable the button.
                        )
                    }
                }
            } else {
                // Stop button:
                WheelSpunButton(
                    turnOrStopMessage = true,   // "Stop"
                    onClick = {
                        wheelSpunState = false
                        isSpun.value = false
                        // Picks a random number from the wheel:
                        gameViewModel.pickPointFromWheel()
                        isGuessed.value = true
                    },
                    enabled = false
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            if (!gameUiState.haveUserSpunWheel) {
                TellToSpinWheel()
            } else {
                PickedFromWheel(gameUiState.assignedPoint)
            }

            if (gameUiState.haveUserGuessed) {
                IsGuessCorrectOrNot(uiState = gameUiState)
                gameUiState.haveUserGuessed = false
            }

            /*
             * There is one instance where one of the words use two rows on the screen. Therefore
             * a scrollable column for the characters are needed.
             */
            LazyColumn {
                item {
                    Characters(
                        row = 1,
                        gameViewModel = gameViewModel,
                        showCharacters = isGuessed.value,
                        isGuessed = isGuessed,
                        uiState = gameUiState,
                    )
                    Characters(
                        row = 2,
                        gameViewModel = gameViewModel,
                        showCharacters = isGuessed.value,
                        isGuessed = isGuessed,
                        uiState = gameUiState
                    )
                    Characters(
                        row = 3,
                        gameViewModel = gameViewModel,
                        showCharacters = isGuessed.value,
                        isGuessed = isGuessed,
                        uiState = gameUiState
                    )
                    Characters(
                        row = 4,
                        gameViewModel = gameViewModel,
                        showCharacters = isGuessed.value,
                        isGuessed = isGuessed,
                        uiState = gameUiState
                    )
                    Characters(
                        row = 5,
                        gameViewModel = gameViewModel,
                        showCharacters = isGuessed.value,
                        isGuessed = isGuessed,
                        uiState = gameUiState
                    )
                }
            }
        }
    }

    /**
     * PlayAgainButton -- Once the game is over, a play again button should appear asking the
     * user if they would like to play again.
     * Then use the navController to navigate back to the MenuScreen.
     */
}

@Composable
fun TopBarDescription(category: String, points: Int, lives: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarTextWithNumber(title = stringResource(id = R.string.point), desc = points)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarTextWithString(title = stringResource(id = R.string.category), desc = category)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarTextWithImage(desc = lives)
        }
    }
}

@Composable
fun TopBarTextWithNumber(title: String, desc: Int) {
    Text(
        text = title,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        color = Color.White
    )
    Text(
        text = desc.toString(),
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Composable
fun TopBarTextWithString(title: String, desc: String) {
    Text(
        text = title,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        color = Color.White
    )
    Text(
        text = desc,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.White
    )
}

@Composable
fun TopBarTextWithImage(desc: Int) {
    Image(
        painter = painterResource(id = R.drawable.heart),
        contentDescription = "heart",
        modifier = Modifier.size(16.dp)
    )
    Text(
        text = desc.toString(),
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.White
    )
}

/**
 * At the moment, the maximum word length supported is 16.
 * This is equivalent to two rows on the GameScreen, which I think is sufficient at this moment.
 * In the future, you could create a custom layout that when the screen limit has been reached,
 * resets the y position to the row below. That way you don't have to explicitly program how
 * many rows should be supported.
 *
 * Since the variable firstRow always needs to be reset for each round, I believe it is fine
 * to have it here instead of in the ViewModel. This also keeps the ViewModel and UiState more
 * general which is easier to understand.
 * @author Yusuf Kara
 */
@Composable
fun Word(wordLength: Int, uiState: GameUiState) {
    var firstRow = 0
    val letter = uiState.currentWord


    if (wordLength >= 8) {
        firstRow = wordLength

        while (firstRow != 8) {
            firstRow--
        }
    }

    Column(modifier = Modifier.padding(bottom = 5.dp)) {
        if (wordLength <= 8) {
            Row {
                for (i in 1..wordLength) {
                    if (uiState.listOfGuessedCharacters.contains(letter[i - 1].toString())) {
                        Letter(letter[i - 1].toString())
                    } else {
                        Letter(" ")
                    }
                }
            }
        }
        if (wordLength > 8) {
            Row {
                for (i in 1..firstRow) {
                    if (uiState.listOfGuessedCharacters.contains(letter[i - 1].toString())) {
                        Letter(letter[i - 1].toString())
                    } else {
                        Letter(" ")
                    }
                }
            }
            Row {
                for (i in 1..wordLength - 8) {
                    if (uiState.listOfGuessedCharacters.contains(letter[i - 1].toString())) {
                        Letter(letter[i - 1].toString())
                    } else {
                        Letter(" ")
                    }
                }
            }
        }
    }
}

@Composable
fun Letter(letter: String) {
    Card(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .size(30.dp)
    ) {
        Text(
            text = letter,
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Characters(
    row: Int,
    gameViewModel: GameViewModel,
    showCharacters: Boolean,
    isGuessed: MutableState<Boolean>,
    uiState: GameUiState,
) {
    gameViewModel.getDanishCharacters(row)

    Row {
        for (i in 0 until gameViewModel.getDanishCharacters(row).size) {
            val isClicked = remember { mutableStateOf(false) }

            val color = if (isClicked.value) remember {
                mutableStateOf(
                    Color(195, 120, 220)
                )
            } else remember {
                mutableStateOf(
                    Color(180, 155, 255)
                )
            }

            if (showCharacters && !uiState.isBankrupt) {
                OutlinedButton(
                    border = BorderStroke(2.dp, Color(180, 155, 255)),
                    onClick = {
                        if (!isClicked.value) {
                            gameViewModel.checkUserGuess(gameViewModel.getDanishCharacters(row)[i].toString())
                            isGuessed.value = false
                            isClicked.value = true
                        } else { /* Do nothing! */
                        }
                    },
                    enabled = true,
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = color.value),
                    shape = RoundedCornerShape(30),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                        .size(33.dp)
                ) {
                    Text(
                        text = gameViewModel.getDanishCharacters(row)[i].toString(),
                        fontFamily = manropeFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                OutlinedButton(
                    onClick = {
                        gameViewModel.checkUserGuess(gameViewModel.getDanishCharacters(row)[i].toString())
                        isGuessed.value = false
                    },
                    enabled = false,
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    shape = RoundedCornerShape(30),
                    modifier = Modifier
                        .padding(top = 10.dp, start = 5.dp, end = 5.dp)
                        .size(33.dp)
                ) {
                    Text(
                        text = gameViewModel.getDanishCharacters(row)[i].toString(),
                        fontFamily = manropeFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 13.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun WheelRotateAnimation(isSpun: Boolean = false) {
    // Allow resume on rotation
    var currentRotation by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(isSpun) {
        if (isSpun) {
            rotation.animateTo(
                targetValue = currentRotation + 5000,
                animationSpec = infiniteRepeatable(
                    animation = tween(16500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        }
    }

    if (!isSpun) {
        StillWheelImage(rotateDegree = rotation.value)
    } else {
        SpinningWheelImage(rotateDegree = rotation.value)
    }
}

@Composable
fun WheelSpunButton(turnOrStopMessage: Boolean, enabled: Boolean, onClick: () -> Unit) {
    if (!turnOrStopMessage) {
        if (enabled) {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(Color(195, 120, 220)),
                enabled = true,
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.spin_wheel),
                    fontFamily = manropeFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        } else {
            Button(
                onClick = onClick,
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(Color(195, 120, 220)),
                enabled = false,
                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.spin_wheel),
                    fontFamily = manropeFamily,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    } else {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .height(40.dp)
        ) {
            Text(
                text = stringResource(id = R.string.stop_wheel),
                fontFamily = manropeFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

/**
 * This function displays the wheel but is only displayed when the wheel is not rotating.
 */
@Composable
fun StillWheelImage(rotateDegree: Float = 0f) {
    val stillWheel = painterResource(id = R.drawable.still_wheel)

    Image(
        painter = stillWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(250.dp)
    )
}

/**
 * This function displays the wheel but is only displayed when the wheel is rotating.
 */
@Composable
fun SpinningWheelImage(rotateDegree: Float = 0f) {
    val spinningWheel = painterResource(id = R.drawable.spinning_wheel)

    Image(
        painter = spinningWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(250.dp)
    )
}

/**
 * This function displays two different texts on the same position, depending on what point
 * the player landed on when spinning the wheel.
 */
@Composable
fun PickedFromWheel(assignedPoint: Int) {
    if (assignedPoint != 0) {
        Text(
            text = "Du rollede $assignedPoint! Gæt et bogstav",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    } else {
        Text(
            text = "Du rollede desværre 0. Du mister derfor alle dine point :(",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TellToSpinWheel() {
    Text(
        text = "Drej hjulet for at gætte et bogstav!",
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun IsGuessCorrectOrNot(uiState: GameUiState) {
    val point = uiState.assignedPoint * uiState.numOfMultiplication

    if (uiState.isGuessedWordCorrect) {
        Text(
            text = "Det er korrekt. Du får $point point",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.Green,
            textAlign = TextAlign.Center
        )
    } else {
        Text(
            text = "Det er ikke korrekt! Du mister et liv",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}