package com.example.weatherapp

sealed class Screen(val route: String) {
    object WeatherPage : Screen("weather_page")
    object DetailsPage : Screen("details_page")
    object SettingsPage : Screen("settings")
}