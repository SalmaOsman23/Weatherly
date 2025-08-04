package com.example.weatherly.ui.theme.screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherly.R
import com.example.weatherly.ui.theme.Routes

@Composable
fun WelcomeScreen(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Respect Scaffold padding
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_illustration),
                    contentDescription = "Weatherly Welcome Illustration",
                    modifier = Modifier.size(300.dp)
                )

                Spacer(modifier = Modifier.height(32.dp))

                FilledTonalButton(
                    onClick = {
                        navController.navigate(Routes.MainScreen) {
                            popUpTo(Routes.welcomeScreen) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = colorResource(id = R.color.dark_primary),
                        contentColor = colorResource(id = R.color.white)
                    )
                ) {
                    Text("Let's Start")
                }
            }
        }
    )
}