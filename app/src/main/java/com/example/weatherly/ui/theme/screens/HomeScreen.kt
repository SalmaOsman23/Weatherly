package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherly.R
import com.example.weatherly.ui.theme.DarkPrimaryColor
import com.example.weatherly.ui.theme.PrimaryColor
import com.example.weatherly.ui.theme.PrimaryColorLighterShade
import com.example.weatherly.ui.theme.components.ErrorComponent
import com.example.weatherly.ui.theme.components.LottieLoadingComponent
import com.example.weatherly.ui.theme.current_weather_api.CurrentWeatherModel
import com.example.weatherly.weather_api.NetworkResponse
import com.example.weatherly.weather_api.WeatherViewModel


@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {
    val currentWeatherResult = viewModel.currentWeatherResult.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getCurrentLocationWeatherData()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val result = currentWeatherResult.value) {
            is NetworkResponse.Error -> {
                ErrorComponent()
            }

            NetworkResponse.Loading -> LottieLoadingComponent()
            is NetworkResponse.Success<*> -> {
                val weatherData = result.data as? CurrentWeatherModel

                if (weatherData == null || weatherData.daily.time.isEmpty()) {
                    LottieLoadingComponent()
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(60.dp))
                        LazyColumn {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp, vertical = 48.dp)
                                        .background(
                                            brush = Brush.linearGradient(
                                                0f to PrimaryColor,
                                                0.5f to DarkPrimaryColor,
                                                1f to PrimaryColorLighterShade
                                            )
                                        )
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.img_clouds),
                                            contentDescription = null,
                                            contentScale = ContentScale.FillWidth,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        Text("Cairo, Egypt")
                                    }

                                }
                            }
                            items(weatherData.daily.time.size) { index ->
                                WeatherCard(
                                    date = weatherData.daily.time[index],
                                    temperature = weatherData.daily.temperature_2m_max[index],
                                    unit = weatherData.daily_units.temperature_2m_max
                                )
                            }
                        }
                    }
                }
            }

            null -> {}
        }
    }
}

@Composable
fun WeatherCard(date: String, temperature: String, unit: String) {
    Column(modifier = Modifier.padding(50.dp)) {
        Text(text = "Date: $date", fontSize = 16.sp)
        Text(text = "Temp: $temperature $unit", fontSize = 16.sp)
    }
}

