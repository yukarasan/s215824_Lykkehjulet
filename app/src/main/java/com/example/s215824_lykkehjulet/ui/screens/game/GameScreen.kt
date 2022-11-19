package com.example.s215824_lykkehjulet.ui.screens.game

import android.content.ClipData
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.model.GameUiState
import com.example.s215824_lykkehjulet.model.GameViewModel

@Composable
fun GameScreen(
    navController: NavController,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()

    /**
     * I could not make the wheel spin work without declaring the states here.
     * Therefore I've decided, even though it is not best practice, to keep them here.
     * But since since the wheel is not a part of the requirements for this assignment, I hope
     * it won't count as a bad thing.
     */
    var wheelSpunState by remember { mutableStateOf(false) }
    var isSpun by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
    ) {
        TopBarDescription(
            category = gameUiState.category,
            lives = gameUiState.lives,
            points = gameUiState.point
        )

        Word(wordLength = gameUiState.wordLength, uiState = gameUiState)

        WheelRotateAnimation(isSpun = wheelSpunState)

        if (!isSpun) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WheelSpunButton(
                    isSpun = false,
                    onClick = {
                        wheelSpunState = true
                        isSpun = true
                    }
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WheelSpunButton(
                    isSpun = true,
                    onClick = {
                        wheelSpunState = false
                        isSpun = false
                        gameViewModel.pickPointFromWheel()
                    }
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            if (!gameUiState.haveUserSpunWheel) {
                TellToSpinWheel()
            } else {
                PickedFromWheel(gameUiState.assignedPoint, gameViewModel)
            }

            LazyColumn() {
                item {
                    Characters(row = 1,
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState,
                        onClick = {
                            gameViewModel.updatePoint()
                        }
                    )
                    Characters(row = 2,
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState,
                        onClick = {
                            gameViewModel.updatePoint()
                        }
                    )
                    Characters(row = 3,
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState,
                        onClick = {
                            gameViewModel.updatePoint()
                        }
                    )
                    Characters(row = 4,
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState,
                        onClick = {
                            gameViewModel.updatePoint()
                        }
                    )
                    Characters(row = 5,
                        gameViewModel = gameViewModel,
                        gameUiState = gameUiState,
                        onClick = {
                            gameViewModel.updatePoint()
                        }
                    )
                }
            }

            // TODO: Remember that a user should not be able to guess more than one time pr. round.
        }
    }

    /**
     * TODO: if the wheel is currently spinning then the buttons should not be clicked.
     * TODO: If the wheel is standing still, the buttons needs to be clicked.
     */


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
            TopBarTextWithNumber(title = stringResource(id = R.string.lives), desc = lives)
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
        color = Color.Black
    )
    Text(
        text = desc.toString(),
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.Black
    )
}

@Composable
fun TopBarTextWithString(title: String, desc: String) {
    Text(
        text = title,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 12.sp,
        color = Color.Black
    )
    Text(
        text = desc,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp,
        color = Color.Black
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
            Row() {
                for (i in 1..wordLength) {  // Should later be: for (item in collection)
                    Letter(letter.get(i - 1))
                }
            }
        }
        if (wordLength > 8) {
            Row() {
                for (i in 1..firstRow) {  // Should later be: for (item in collection)
                    Letter(letter.get(i - 1))
                }
            }
            Row() {
                for (i in 1..wordLength - 8) {  // Should later be: for (item in collection)
                    Letter(letter.get(8 + (i - 1)))
                }
            }
        }
    }
}

@Composable
fun Letter(letter: Char) {
    Card(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .size(30.dp)
    ) {
        Text(
            text = letter.toString(),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Characters(
    row: Int,
    gameViewModel: GameViewModel,
    gameUiState: GameUiState,
    onClick: () -> Unit
) {
    gameViewModel.getDanishCharacters(row)

    Row() {
        for (i in 0 until gameViewModel.getDanishCharacters(row).size) {
            LettersToChooseFrom(
                character = gameViewModel.getDanishCharacters(row)[i],
                gameViewModel = gameViewModel,
                gameUiState = gameUiState,
                onClick = onClick
            )
        }
    }
}

@Composable
fun LettersToChooseFrom(
    character: Char,
    gameViewModel: GameViewModel,
    gameUiState: GameUiState,
    onClick: () -> Unit
) {
    OutlinedButton(
        border = BorderStroke(2.dp, Color.Black),
        onClick = onClick,
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .size(33.dp)
    ) {
        Text(
            text = character.toString(),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 13.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
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
    } else if (isSpun) {
        SpinningWheelImage(rotateDegree = rotation.value)
    }
}

@Composable
fun WheelSpunButton(isSpun: Boolean, onClick: () -> Unit) {
    if (!isSpun) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
            modifier = Modifier
                .height(40.dp)
                .padding(start = 115.dp, end = 115.dp)
                .fillMaxWidth()
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
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            modifier = Modifier
                .height(40.dp)
                .padding(start = 115.dp, end = 115.dp)
                .fillMaxWidth()
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
 * @author Yusuf Kara
 */
@Composable
fun StillWheelImage(rotateDegree: Float = 0f) {
    val stillWheel = painterResource(id = R.drawable.wheelstill)

    Image(
        painter = stillWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(150.dp)
    )
}

/**
 * This function displays the wheel but is only displayed when the wheel is rotating.
 * @author Yusuf Kara
 */
@Composable
fun SpinningWheelImage(rotateDegree: Float = 0f) {
    val spinningWheel = painterResource(id = R.drawable.wheelspinning)

    Image(
        painter = spinningWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(150.dp)
    )
}

/**
 * This function displays two different texts on the same position, depending on what point
 * the player landed on when spinning the wheel.
 *
 * If the player landed on 0, then
 */
@Composable
fun PickedFromWheel(assignedPoint: Int, gameViewModel: GameViewModel) {
    if (assignedPoint != 0) {
        Text(
            text = "Du rollede $assignedPoint! Gæt et bogstav",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    } else {
        Text(
            text = "Du rollede desværre $assignedPoint. Du mister derfor alle dine point :(",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        // TODO: Reduce amount of points here with a function from ViewModel
    }
}

@Composable
fun TellToSpinWheel() {
    Text(
        text = "Drej hjullet for at gætte et bogstav!",
        fontFamily = manropeFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
fun IsGuessCorrectOrNot(isGuessCorrect: Boolean) {
    if (isGuessCorrect) {
        Text(text = "That is correct")
    } else {
        Text(text = "That is NOT correct")
    }
}

// TODO: Remove the preview once done. Because it does not look good.
@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(navController = rememberNavController())
}