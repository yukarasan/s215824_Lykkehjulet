package com.example.s215824_lykkehjulet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.model.ViewModel
import com.example.s215824_lykkehjulet.navigation.SetupNavGraph

@Composable
fun App(viewModel: ViewModel = viewModel()) {
    // ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Navigation
    val navController: NavHostController = rememberNavController()
    SetupNavGraph(navController = navController, uiState = uiState)
}