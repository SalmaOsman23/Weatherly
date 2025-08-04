package com.example.weatherly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherly.ui.theme.screens.MainScreen
import com.example.weatherly.ui.theme.Routes
import com.example.weatherly.ui.theme.screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.welcomeScreen
            ) {
                composable(Routes.welcomeScreen) {
                    WelcomeScreen(navController)
                }
                composable(Routes.MainScreen) {
                    MainScreen(navController)
                }
            }
        }
    }
}

