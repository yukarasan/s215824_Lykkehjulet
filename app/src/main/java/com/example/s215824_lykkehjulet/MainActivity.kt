package com.example.s215824_lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.s215824_lykkehjulet.model.App
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

/* General TO-DO list:
 *
 * TODO: When screen is tilt, make sure that the activities don't restart.
 * YouTube link:
 * https://www.youtube.com/watch?v=NVrFiogpyr8&list=PLSrm9z4zp4mEWwyiuYgVMWcDFdsebhM-r&index=51&ab_channel=Stevdza-San
 *
 *
 *
 *
 */