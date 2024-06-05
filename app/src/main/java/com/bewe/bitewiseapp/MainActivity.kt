package com.bewe.bitewiseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bewe.bitewiseapp.ui.theme.BiteWiseAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiteWiseAppTheme {
                BiteWiseApp()
            }
        }
    }
}
