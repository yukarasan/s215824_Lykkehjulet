package com.example.s215824_lykkehjulet.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.s215824_lykkehjulet.ui.navigation.Screen

@Composable
fun GameWonScreen(
    navController: NavController,
    gameViewModel: GameViewModel,
    point: Int,
    lives: Int,
    word: String,
    category: String,
    attempts: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(90, 49, 160))
            .padding(top = 100.dp)
    ) {
        Text(
            text = stringResource(R.string.you_won_the_game),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.your_statistics),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = buildString {
                append(stringResource(R.string.point))
                append(": ")
                append(point)
            },
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Liv: $lives",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Ordet var: $word",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Kategorien: $category",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "Antal forsøg: $attempts",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = stringResource(R.string.want_to_play_again),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 22.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }


    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp, bottom = 100.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            PlayAgainButton(gameViewModel, navController)
            GoToMenuButton(gameViewModel, navController)
        }
    }
}

@Composable
private fun PlayAgainButton(gameViewModel: GameViewModel, navController: NavController) {
    Button(
        onClick = {
            navController.navigate(Screen.GameScreen.route)
            gameViewModel.playAgain()
        },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(195, 120, 220)),
        enabled = true,
        modifier = Modifier
            .height(60.dp)
    ) {
        Text(
            text = stringResource(R.string.play_again),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
private fun GoToMenuButton(gameViewModel: GameViewModel, navController: NavController) {
    Button(
        onClick = {
            navController.navigate(Screen.MenuScreen.route)
            gameViewModel.playAgain()
        },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(180, 155, 255)),
        enabled = true,
        modifier = Modifier
            .height(60.dp)
            .width(120.dp)
    ) {
        Text(
            text = stringResource(R.string.menu),
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp,
            color = Color.White
        )
    }
}