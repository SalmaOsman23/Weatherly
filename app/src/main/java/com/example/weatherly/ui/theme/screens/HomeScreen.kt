package com.example.weatherly.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.weatherly.ui.theme.components.ErrorComponent
import com.example.weatherly.ui.theme.components.LottieLoadingComponent
import com.example.weatherly.ui.theme.current_weather_api.CurrentWeatherApi
import com.example.weatherly.weather_api.NetworkResponse
import com.example.weatherly.weather_api.WeatherViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: WeatherViewModel){
    val currentWeatherResult = viewModel.currentWeatherResult.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getCurrentLocationWeatherData()
    }
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        when(val result = currentWeatherResult.value){
            is NetworkResponse.Error -> {
                ErrorComponent()
            }
            NetworkResponse.Loading -> LottieLoadingComponent()
            is NetworkResponse.Success<*> -> {
                Text(text = result.data.toString())
                Log.i("Response Current Location",result.data.toString())
            }
            null -> {}
        }
    }
}