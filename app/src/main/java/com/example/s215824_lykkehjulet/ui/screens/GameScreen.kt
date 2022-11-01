package com.example.s215824_lykkehjulet.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.ui.MenuScreen

@Composable
fun GameScreen(navController: NavController) {
    var wheelSpunState by remember { mutableStateOf(false) }
    var isSpun by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center) {
        Column() {
            WheelRotateAnimation(isSpun = wheelSpunState)

            if (!isSpun) {
                WheelSpunButton(
                    onClick = {
                        wheelSpunState = true
                        isSpun = true
                    },
                    isSpun = isSpun
                )
            } else {
                WheelSpunButton(
                    onClick = {
                        wheelSpunState = false
                        isSpun = false
                    },
                    isSpun = isSpun
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
fun WheelRotateAnimation(isSpun: Boolean = false) {
    // Allow resume on rotation
    var currentRotation by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(isSpun) {
        if (isSpun) {
            rotation.animateTo(
                targetValue = currentRotation + 2016,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        }
    }
    WheelImage(rotateDegree = rotation.value)

}

@Composable
fun WheelSpunButton(isSpun: Boolean, onClick: () -> Unit) {
    if (!isSpun) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(100),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(115, 115, 115)),
            modifier = Modifier
                .height(40.dp)
                .padding(start = 20.dp)
        ) {
            Text(
                text = "Drej hjullet",
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
                .padding(start = 20.dp)
        ) {
            Text(
                text = "Stop",
                fontFamily = manropeFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun WheelImage(rotateDegree: Float = 0f) {
    val image = painterResource(id = R.drawable.danishwheel)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .rotate(rotateDegree)
            .size(300.dp)
    )
}

@Preview
@Composable
fun GameScreenPreview() {
    MenuScreen(navController = rememberNavController())
}