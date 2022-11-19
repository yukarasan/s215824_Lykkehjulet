package com.example.s215824_lykkehjulet.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily
import com.example.s215824_lykkehjulet.navigation.Screen

/**
 * This screen is stateless. It only uses the navController.
 * From this screen, a user is able to either navigate to the rules of the game, or navigate
 * to the GameScreen and start playing.
 */
@Composable
fun MenuScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GameTitle(
                title = stringResource(id = R.string.game_title),
                modifier = Modifier.padding(bottom = 60.dp)
            )
            SeeRulesButton(
                navController,
                modifier = Modifier
                    .width(185.dp)
                    .padding(bottom = 40.dp)
            )
            PlayGameButton(
                navController,
                modifier = Modifier
                    .width(185.dp)
            )
        }
    }
}

@Composable
private fun GameTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        color = Color.Black,
        fontSize = 50.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
private fun SeeRulesButton(
    navController: NavController,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier
) {
    Button(
        onClick = { navController.navigate(route = Screen.RulesScreen.route) },
        elevation = ButtonDefaults.elevation(12.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(217, 217, 217)),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.see_rules),
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Bold,
            modifier = textModifier
        )
    }
}

@Composable
private fun PlayGameButton(
    navController: NavController,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier
) {
    Button(
        onClick = { navController.navigate(Screen.GameScreen.route) },
        elevation = ButtonDefaults.elevation(12.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color.Black),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.play),
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = manropeFamily,
            fontWeight = FontWeight.Bold,
            modifier = textModifier
        )
    }
}

@Preview
@Composable
fun MenuScreenPreview() {
    MenuScreen(navController = rememberNavController())
}