package com.bewe.bitewiseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            BiteWiseAppTheme(darkTheme = false) {
                BiteWiseApp()
            }
        }
    }
}
