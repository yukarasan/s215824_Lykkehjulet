package com.example.s215824_lykkehjulet

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.ui.navigation.SetupNavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun App(context: Context) {
    // Navigation
    val navController: NavHostController = rememberNavController()
    SetupNavGraph(navController = navController)

    // Locking the screen rotation
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

    val systemUiController = rememberSystemUiController()

    /*
     * Setting the color of the status bar and navigation bar
     * Source: https://google.github.io/accompanist/systemuicontroller/
     */
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(0xFF533A84),
            darkIcons = false
        )
        systemUiController.setNavigationBarColor(
            color = Color(0xFF161A1E),
            darkIcons = false
        )
    }
}