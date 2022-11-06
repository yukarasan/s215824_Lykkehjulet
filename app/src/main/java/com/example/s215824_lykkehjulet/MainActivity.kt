package com.example.s215824_lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.s215824_lykkehjulet.view.theme.S215824LykkehjuletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S215824LykkehjuletTheme {
                App()
            }
        }
    }
}