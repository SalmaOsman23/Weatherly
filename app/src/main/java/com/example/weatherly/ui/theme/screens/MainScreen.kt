package com.example.weatherly.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherly.weather_api.WeatherViewModel
import com.example.weatherly.ui.theme.NavItem


@Composable
fun MainScreen(navController: NavController) {
    val navItemList = listOf(
        NavItem("Profile", Icons.Outlined.Person, Icons.Filled.Person),
        NavItem("Settings", Icons.Outlined.Settings, Icons.Filled.Settings),
        NavItem("Search", Icons.Outlined.Search, Icons.Filled.Search),
        NavItem("Home", Icons.Outlined.Home, Icons.Filled.Home),

        )
    var selected by remember {
        mutableIntStateOf(0)
    }
    val weatherViewModel: WeatherViewModel = viewModel()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
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
        ContentScreen(modifier = Modifier.padding(innerPadding), selected,weatherViewModel = weatherViewModel)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int,weatherViewModel: WeatherViewModel) {
    when (selectedIndex) {
        3 -> HomeScreen(modifier,weatherViewModel)
        2 -> SearchScreen(weatherViewModel)
        1 -> ProfileScreen()
        0 -> SettingsScreen()
    }
}


//data class BottomNavigationItem(
//    val title:String,
//    val selectedIcon: ImageVector,
//    val unSelectedIcon: ImageVector,
//)
//sealed class Screen(val route: String, val icon: ImageVector, val selectedIcon: ImageVector) {
//    object Home : Screen("home", Icons.Outlined.Home, Icons.Filled.Home)
//    object Profile : Screen("profile", Icons.Outlined.Person, Icons.Filled.Person)
//    object Settings : Screen("settings", Icons.Outlined.Settings, Icons.Filled.Settings)
//}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MainScreen(navController: NavController) {
//    val bottomNavController = rememberNavController()
//
//    val screens = listOf(
//        Screen.Home,
//        Screen.Profile,
//        Screen.Settings
//    )
//    val currentRoute = bottomNavController.currentBackStackEntry?.destination?.route
//
//    Scaffold(
//        topBar = { TopAppBar(title = { Text("Weatherly") }) },
//        bottomBar = {
//            NavigationBar {
//                screens.forEach { screen ->
//                    val selected = currentRoute == screen.route
//                    NavigationBarItem(
//                        icon = {
//                            Icon(
//                                imageVector = if (selected) screen.selectedIcon else screen.icon,
//                                contentDescription = screen.route
//                            )
//                        },
//                        selected = selected,
//                        onClick = {
//                            bottomNavController.navigate(screen.route) {
//                                launchSingleTop = true
//                                restoreState = true
//                                popUpTo(bottomNavController.graph.startDestinationId) {
//                                    saveState = true
//                                }
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    ) { padding ->
//        NavHost(
//            navController = bottomNavController,
//            startDestination = Screen.Home.route,
//            modifier = Modifier.padding(padding)
//        ) {
//            composable(Screen.Home.route) { HomeScreen() }
//            composable(Screen.Profile.route) { ProfileScreen() }
//            composable(Screen.Settings.route) { SettingsScreen() }
//        }
//    }
//}

//@Composable
//fun AppNavGraph(navController: NavHostController) {
//    NavHost(navController = navController, startDestination = Screen.Profile.route) {
//        composable(Screen.Profile.route) { ProfileScreen() }
//        composable(Screen.Home.route) { HomeScreen() }
//        composable(Screen.Settings.route) { SettingsScreen() }
//    }
//}


