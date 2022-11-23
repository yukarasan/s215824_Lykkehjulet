package com.example.s215824_lykkehjulet.ui.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.model.GameUiState
import com.example.s215824_lykkehjulet.model.GameViewModel
import com.example.s215824_lykkehjulet.navigation.Screen

@Composable
fun GameLostScreen(
    navController: NavController,
    gameUiState: GameUiState,
    gameViewModel: GameViewModel
) {
    val word = gameUiState.currentWord
    val point = gameUiState.point
    val totalGuesses = gameUiState.numOfTotalGuesses

    /**
     * Show the user that they have lost the game.
     * Ask them if they would like to play again.
     */
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(90, 49, 160))
    ) {
        Text(
            text = "Du har mistet alle dine liv og spillet er derfor slut.",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Her er dine statistikker: ",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = "Point: $point",
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
            text = "Antal forsøg: $totalGuesses",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = "Vil du gerne spille igen?",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp)
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
}

@Composable
private fun PlayAgainButton(gameViewModel: GameViewModel, navController: NavController) {
    Button(
        onClick = {
            gameViewModel.playAgain()
            navController.navigate(Screen.GameScreen.route)
        },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(195, 120, 220)),
        enabled = true,
        modifier = Modifier
            .height(40.dp)
    ) {
        Text(
            text = "Spil igen",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
private fun GoToMenuButton(gameViewModel: GameViewModel, navController: NavController) {
    Button(
        onClick = {
            gameViewModel.playAgain()
            navController.navigate(Screen.MenuScreen.route)
        },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(180, 155, 255)),
        enabled = true,
        modifier = Modifier
            .height(40.dp)
    ) {
        Text(
            text = "Menu",
            fontFamily = manropeFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}