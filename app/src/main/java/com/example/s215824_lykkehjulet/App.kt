package com.example.s215824_lykkehjulet

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.model.GameViewModel
import com.example.s215824_lykkehjulet.navigation.SetupNavGraph

@Composable
fun App(context: Context) {
    // Navigation
    val navController: NavHostController = rememberNavController()
    SetupNavGraph(navController = navController)

    // Locking the screen rotation
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED
}