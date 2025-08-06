package com.example.weatherly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherly.ui.theme.Routes
import com.example.weatherly.ui.theme.screens.MainScreen
import com.example.weatherly.ui.theme.screens.WelcomeScreen
import com.example.weatherly.weather_api.WeatherViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                val navController = rememberNavController()
                val viewModel : WeatherViewModel = viewModel()
                NavHost(
                    navController = navController,
                    startDestination = Routes.WelcomeScreenRoute
                ) {
                    composable(Routes.WelcomeScreenRoute) {
                        WelcomeScreen(navController,viewModel)
                    }
                    composable(Routes.MainScreenRoute) {
                        MainScreen(navController)
                    }
                }
            }
        }
    }
}
