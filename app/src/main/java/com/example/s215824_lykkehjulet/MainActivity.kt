package com.example.s215824_lykkehjulet

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.s215824_lykkehjulet.view.theme.S215824LykkehjuletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S215824LykkehjuletTheme {
                App(context = LocalContext.current)
            }
        }
    }
}