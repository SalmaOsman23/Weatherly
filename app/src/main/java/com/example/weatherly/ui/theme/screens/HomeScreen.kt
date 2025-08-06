package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherly.R
import com.example.weatherly.ui.theme.DarkBlue
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val result = currentWeatherResult.value) {
            is NetworkResponse.Error -> {
                ErrorComponent(modifier = Modifier.weight(1f))
            }

            NetworkResponse.Loading -> LottieLoadingComponent(modifier = Modifier.weight(1f))
            is NetworkResponse.Success<*> -> {
                val weatherData = result.data as? CurrentWeatherModel

                if (weatherData == null || weatherData.daily.time.isEmpty()) {
                    LottieLoadingComponent(modifier = Modifier.weight(1f))
                } else {
                    Spacer(Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp, vertical = 16.dp)
                                    .border(
                                        3.dp,
                                        color = DarkPrimaryColor,
                                        RoundedCornerShape(20.dp)
                                    )
                                    .background(
                                        brush = Brush.linearGradient(
                                            0f to PrimaryColor,
                                            0.5f to DarkPrimaryColor,
                                            1f to PrimaryColorLighterShade
                                        ),
                                        shape = RoundedCornerShape(20.dp)
                                    )
                            )
                            {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Column(
                                        modifier = Modifier.weight(1f),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center,
                                    ) {
                                        Text(
                                            "Cairo, Egypt",
                                            fontSize = 34.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                    Column(
                                        modifier = Modifier.padding(start = 8.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = R.drawable.img_clouds),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(120.dp)
                                                .clip(RoundedCornerShape(16.dp))
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 24.dp, bottom = 12.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    "Cairo's Weather For the Next 7 Days",
                                    fontWeight = FontWeight.Bold,
                                    color = DarkPrimaryColor,
                                    fontSize = 20.sp
                                )
                            }
                        }

                        items(weatherData.daily.time.size) { index ->
                            WeatherCard(
                                date = weatherData.daily.time[index],
                                temperature = weatherData.daily.temperature_2m_max[index].toString(),
                                unit = weatherData.daily_units.temperature_2m_max
                            )
                        }
                    }
                }
            }

            null -> {
                LottieLoadingComponent(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun WeatherCard(date: String, temperature: String, unit: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 24.dp)
            .border(
                3.dp,
                color = DarkPrimaryColor,
                RoundedCornerShape(20.dp)
            )
            .background(
                color = PrimaryColorLighterShade,
                shape = RoundedCornerShape(20.dp)
            ),

        ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Date: ",
                    fontSize = 20.sp,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = date,
                    fontSize = 18.sp,
                    color = DarkPrimaryColor,
                    fontWeight = FontWeight.SemiBold
                )

            }
            Row {
                Text(
                    text = "Temp: ",
                    fontSize = 20.sp,
                    color = DarkBlue,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$temperature $unit",
                    fontSize = 18.sp,
                    color = DarkPrimaryColor,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }
    }
}

