package com.example.appnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appnote.ui.screens.HomeScreen
import com.example.appnote.ui.screens.LoginScreen
import com.example.appnote.ui.navigation.AppNavGraph

import com.example.appnote.ui.theme.AppNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            AppNoteTheme {
                // Start your main Composable
                //LoginScreen()
                //HomeScreen()
                AppNavGraph()
            }
        }
    }
}
