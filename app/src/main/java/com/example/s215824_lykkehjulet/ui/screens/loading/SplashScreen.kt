package com.example.s215824_lykkehjulet.ui.screens.loading

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.s215824_lykkehjulet.R
import com.example.s215824_lykkehjulet.ui.navigation.Screen
import kotlinx.coroutines.delay

/**
 * Splash screen.
 * Source: https://www.geeksforgeeks.org/animated-splash-screen-in-android-using-jetpack-compose
 */
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
        navController.navigate(Screen.MenuScreen.route)
    }

    // Image
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0.0f to Color(0xFF533A86),
                    0.7f to Color(0xFF161A1E)
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.still_wheel),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .size(300.dp)
        )
    }
}