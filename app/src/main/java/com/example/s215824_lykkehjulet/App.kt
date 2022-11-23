package com.example.s215824_lykkehjulet

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.Window
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.s215824_lykkehjulet.navigation.SetupNavGraph

@Composable
fun App(context: Context) {
    // Navigation
    val navController: NavHostController = rememberNavController()
    SetupNavGraph(navController = navController)

    // Locking the screen rotation
    val activity = context as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED

    activity.window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
}