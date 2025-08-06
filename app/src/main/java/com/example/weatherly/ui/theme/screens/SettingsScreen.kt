package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import com.example.weatherly.R
import com.example.weatherly.ui.theme.Routes

@Composable
fun SettingsScreen(modifier: Modifier = Modifier,navController: NavController){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        FilledTonalButton(
            onClick = {
                navController.navigate(Routes.WelcomeScreenRoute)
            },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = colorResource(id = R.color.dark_primary),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Text("Go to Welcome Screen")
        }
    }
}