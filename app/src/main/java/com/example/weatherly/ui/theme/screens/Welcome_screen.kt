package com.example.weatherly.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherly.R
import com.example.weatherly.ui.theme.DarkPrimaryColor
import com.example.weatherly.ui.theme.Routes
import com.example.weatherly.ui.theme.components.LottieLoadingComponent
import com.example.weatherly.weather_api.NetworkResponse
import com.example.weatherly.weather_api.WeatherViewModel

@Composable
fun WelcomeScreen(navController: NavController, viewModel: WeatherViewModel) {
    val successResult by viewModel.successResult.observeAsState()
    val context = LocalContext.current

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (successResult) {
                    is NetworkResponse.Loading -> {
                        LottieLoadingComponent(modifier = Modifier.fillMaxSize())
                    }
                    is NetworkResponse.Success -> {
                        val successData = (successResult as NetworkResponse.Success).data
                        Toast.makeText(context, "API Response: ${successData.success}", Toast.LENGTH_SHORT).show()
                        navController.navigate(Routes.MainScreenRoute)
                        viewModel.resetSuccessState()
                    }

                    is NetworkResponse.Error -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Text(
                            "Welcome",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkPrimaryColor
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.welcome_illustration),
                            contentDescription = "Weatherly Welcome Illustration",
                            modifier = Modifier.size(300.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        FilledTonalButton(
                            onClick = {
                                viewModel.getSuccessResponse()
                            },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = colorResource(id = R.color.dark_primary),
                                contentColor = colorResource(id = R.color.white)
                            )
                        ) {
                            Text("Let's Start", fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    )
}

