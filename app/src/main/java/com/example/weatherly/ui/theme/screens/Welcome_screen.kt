package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherly.R

@Composable
fun WelcomeScreen() {
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
                    onClick = {},
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