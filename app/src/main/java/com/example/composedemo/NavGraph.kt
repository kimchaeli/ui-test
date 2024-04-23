package com.example.composedemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
//import com.example.composedemo.ui.screens.HomeScreen
//import com.example.composedemo.ui.screens.SettingsScreen
//import com.example.composedemo.ui.screens.MapScreen
//import com.example.composedemo.ui.screens.BottomNavScreen
import com.example.yourapp.HomeScreen
import com.example.yourapp.MapScreen
import com.example.yourapp.SettingsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("settings") { SettingsScreen() }
        composable("map") { MapScreen() }
        navigation(startDestination = "bottomNavScreen", route = "bottomNav") {
            composable("bottomNavScreen") { BottomNavScreen(navController) }
        }
    }
}
