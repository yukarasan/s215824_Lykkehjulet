package com.example.s215824_lykkehjulet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.manropeFamily

/**
 * Since there are already a predefined sets of rules, a list of items will be sufficient here.
 */
@Composable
fun RulesScreen(navController: NavController) {
    val scrollPosition = rememberLazyListState()

    LazyColumn(
        state = scrollPosition,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        contentPadding = PaddingValues(30.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            BackButton(
                navController,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.one_player),
                description = stringResource(id = R.string.one_player_desc),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.bank),
                description = stringResource(id = R.string.bank_desc),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.game_start),
                description = stringResource(id = R.string.random_word),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.round),
                description = stringResource(id = R.string.round_desc),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.guess),
                description = stringResource(id = R.string.guess_desc),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            RuleBox(
                title = stringResource(id = R.string.life),
                description = stringResource(id = R.string.life_desc),
                titleModifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                descriptionModifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun BackButton(navController: NavController, modifier: Modifier = Modifier) {
    Button(
        onClick = { navController.popBackStack() }, // Instead of navigating, we pop the stack
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun RuleBox(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleModifier: Modifier = Modifier,
    descriptionModifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color(115, 115, 115),
        elevation = 5.dp,
        modifier = modifier
    ) {
        Column() {
            Text(
                text = title,
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = manropeFamily,
                fontWeight = FontWeight.Bold,
                modifier = titleModifier
            )
            Text(
                text = description,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = manropeFamily,
                fontWeight = FontWeight.Bold,
                modifier = descriptionModifier
            )
        }
    }
}

@Preview
@Composable
fun RulesScreenPreview() {
    MenuScreen(navController = rememberNavController())
}