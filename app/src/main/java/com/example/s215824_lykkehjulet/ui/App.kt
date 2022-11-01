package com.example.s215824_lykkehjulet.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.navigation.SetupNavGraph

@Composable
fun App() {
    val navController: NavHostController = rememberNavController()
    SetupNavGraph(navController = navController)
}