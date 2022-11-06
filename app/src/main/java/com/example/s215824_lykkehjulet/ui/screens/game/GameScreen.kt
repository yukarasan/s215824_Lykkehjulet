package com.example.s215824_lykkehjulet.ui.screens.game

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.model.UiState

@Composable
fun GameScreen(navController: NavController, uiState: UiState) {      // TODO: Maybe remove this nav.
    var wheelSpunState by remember { mutableStateOf(false) }
    var isSpun by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TopBarDescription(category = uiState.category)
        Word(wordLength = 16, letter = "A")
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column() {
            WheelRotateAnimation(isSpun = wheelSpunState)

            if (!isSpun) {
                WheelSpunButton(
                    isSpun = true,
                    onClick = {
                        wheelSpunState = true
                        isSpun = true
                    }
                )
            } else {
                WheelSpunButton(
                    isSpun = false,
                    onClick = {
                        wheelSpunState = false
                        isSpun = false
                    }
                )
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
fun TopBarDescription(category: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarText(title = stringResource(id = R.string.point), desc = "0")
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarText(title = stringResource(id = R.string.category), desc = category)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopBarText(title = stringResource(id = R.string.lives), desc = "5")
        }
    }
}

@Composable
fun TopBarText(title: String, desc: String) {
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
 * At the moment, the maximum word length is 16.
 * This is equivalent to two rows on the GameScreen, which I think is sufficient at this moment.
 * In the future, you could create a custom layout that when the screen limit has been reached,
 * resets the y position to the row below. That way you don't have to explicitly create rows.
 *
 * Since the variable firstRow always needs to be reset for each round, I believe it is fine
 * to have it here instead of in the ViewModel. This also keeps the ViewModel and UiState more
 * general which is easier to understand.
 */
@Composable
fun Word(wordLength: Int, letter: String) {
    var firstRow = 0

    if (wordLength >= 8) {
        firstRow = wordLength

        while (firstRow != 8) {
            firstRow--
        }
    }

    Column() {
        if (wordLength <= 8) {
            Row() {
                for (i in 1..wordLength) {  // Should later be: for (item in collection)
                    Letter(letter)
                }
            }
        }
        if (wordLength > 8) {
            Row() {
                for (i in 1..firstRow) {  // Should later be: for (item in collection)
                    Letter(letter)
                }
            }
            Row() {
                for (i in 1..wordLength - 8) {  // Should later be: for (item in collection)
                    Letter(letter)
                }
            }
        }
    }
}

@Composable
fun Letter(letter: String) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .padding(top = 20.dp, start = 5.dp, end = 5.dp)
            .size(30.dp)
    ) {
        Text(
            text = letter, fontFamily = manropeFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
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

@Composable
fun StillWheelImage(rotateDegree: Float = 0f) {
    val stillWheel = painterResource(id = R.drawable.wheelstill)

    Image(
        painter = stillWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(250.dp)
    )
}

@Composable
fun SpinningWheelImage(rotateDegree: Float = 0f) {
    val spinningWheel = painterResource(id = R.drawable.wheelspinning)

    Image(
        painter = spinningWheel,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .fillMaxWidth()
            .size(250.dp)
    )
}