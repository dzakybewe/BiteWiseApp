package com.bewe.bitewiseapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiteWiseAppTheme {
                SplashScreen {
                    navigateToMain()
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

@Composable
private fun SplashScreen(onAnimationEnd: () -> Unit) {
    val alphaAnim = remember {
        Animatable(0f)
    }
    
    LaunchedEffect(key1 = true) {
        alphaAnim.animateTo(
            1f,
            animationSpec = tween(2000)
        )
        delay(1000)
        onAnimationEnd()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.alpha(alphaAnim.value),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSplashScreen() {
    BiteWiseAppTheme {
        SplashScreen {}
    }
}