package com.example.s215824_lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.s215824_lykkehjulet.view.theme.Lykkehjulet

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lykkehjulet {
                App(context = LocalContext.current)
            }
        }
    }
}