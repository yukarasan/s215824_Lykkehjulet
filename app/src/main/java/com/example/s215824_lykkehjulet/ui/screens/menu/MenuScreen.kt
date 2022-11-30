package com.example.s215824_lykkehjulet.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
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
import com.example.s215824_lykkehjulet.ui.navigation.Screen

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
            .background(
                verticalGradient(
                    0.0f to Color(0xFF533A86),
                    0.7f to Color(0xFF161A1E)
                )
            )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GameTitle(
                title = stringResource(id = R.string.game_title),
                modifier = Modifier.padding(bottom = 60.dp)
            )
            SeeRulesButton(
                navController,
                modifier = Modifier
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
        color = Color.White,
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
        elevation = ButtonDefaults.elevation(10.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(153, 53, 182, 255)),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.see_rules),
            color = Color.White,
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
        elevation = ButtonDefaults.elevation(10.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(155, 107, 254, 255)),
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