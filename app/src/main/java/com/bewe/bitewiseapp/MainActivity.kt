package com.bewe.bitewiseapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bewe.bitewiseapp.pref.SharedPreferencesHelper
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val userId = SharedPreferencesHelper.getUserId(this)
        if (userId == null) {
            val newUserId = UUID.randomUUID().toString()
            SharedPreferencesHelper.saveUserId(this, newUserId)
        }

        if (BuildConfig.DEBUG) {
            Log.d("User UUID", userId.toString())
        }

        setContent {
            BiteWiseAppTheme {
                BiteWiseApp()
            }
        }
    }
}
