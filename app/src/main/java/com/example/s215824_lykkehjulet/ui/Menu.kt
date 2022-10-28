package com.example.s215824_lykkehjulet.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.data.manropeFamily

@Preview    // TODO: Remove preview in future
@Composable
fun Menu() {
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
                modifier = Modifier
                    .width(185.dp)
                    .padding(bottom = 40.dp)
            )
            PlayGameButton(
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
private fun SeeRulesButton(modifier: Modifier = Modifier, textModifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
        elevation = ButtonDefaults.elevation(12.dp),
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(Color(115, 115, 115)),
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
private fun PlayGameButton(modifier: Modifier = Modifier, textModifier: Modifier = Modifier) {
    Button(
        onClick = { /*TODO*/ },
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