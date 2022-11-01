package com.example.s215824_lykkehjulet.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.ui.MenuScreen

@Composable
fun GameScreen(navController: NavController) {





    /**
     * PlayAgainButton -- Once the game is over, a play again button should appear asking the
     * user if they would like to play again.
     * Then use the navController to navigate back to the MenuScreen.
     */
}

@Preview
@Composable
fun GameScreenPreview() {
    MenuScreen(navController = rememberNavController())
}