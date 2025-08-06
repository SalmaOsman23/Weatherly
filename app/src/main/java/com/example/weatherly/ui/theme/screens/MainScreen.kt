package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.weatherly.ui.theme.DarkPrimaryColor
import com.example.weatherly.ui.theme.NavItem
import com.example.weatherly.ui.theme.PrimaryColor
import com.example.weatherly.ui.theme.PrimaryColorLighterShade
import com.example.weatherly.ui.theme.White
import com.example.weatherly.weather_api.WeatherViewModel


@Composable
fun MainScreen(navController: NavController) {
    val navItemList = listOf(
        NavItem("Home", Icons.Outlined.Home, Icons.Filled.Home),
        NavItem("Search", Icons.Outlined.Search, Icons.Filled.Search),
        NavItem("Settings", Icons.Outlined.Settings, Icons.Filled.Settings),
        NavItem("Profile", Icons.Outlined.Person, Icons.Filled.Person),

        )
    var selected by remember {
        mutableIntStateOf(0)
    }
    val weatherViewModel: WeatherViewModel = viewModel()
    val gradientColors = listOf(PrimaryColor, DarkPrimaryColor, PrimaryColorLighterShade)
    val appbarBrush = Brush.horizontalGradient(gradientColors)


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            (CenterAlignedTopAppBar(
                modifier = Modifier.background(DarkPrimaryColor),
                title = {
                    Text(
                        "Weatherly", fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )


                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = White
                )
            ))
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(appbarBrush),
                containerColor = Color.Transparent
            ) {

                navItemList.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            if (selected == index) Icon(
                                imageVector = item.selectedIcon,
                                contentDescription = " Selected Icon"
                            ) else Icon(imageVector = item.icon, contentDescription = "Icon")
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            modifier = Modifier.padding(innerPadding),
            selected,
            weatherViewModel = weatherViewModel,
            navController
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    weatherViewModel: WeatherViewModel,
    navController: NavController
) {
    when (selectedIndex) {
        0 -> HomeScreen(modifier, weatherViewModel)
        1 -> SearchScreen(weatherViewModel, modifier)
        2 -> SettingsScreen(modifier, navController)
        3 -> ProfileScreen(modifier)
    }
}


