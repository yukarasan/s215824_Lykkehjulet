package com.example.s215824_lykkehjulet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Since there are already a predefined sets of rules, a list of items will be sufficient here.
 */
@Preview
@Composable
fun RulesScreen() {
    val scrollPosition = rememberLazyListState()

    LazyColumn(
        state = scrollPosition,
        verticalArrangement = Arrangement.spacedBy(33.dp),
        contentPadding = PaddingValues(top = 20.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        item { BackButton() }
        item { }
        item { }
        item { }
        item { }
    }
}

@Composable
private fun BackButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
        modifier = Modifier
            .height(50.dp)
            .width(50.dp)
    ) {

    }
}