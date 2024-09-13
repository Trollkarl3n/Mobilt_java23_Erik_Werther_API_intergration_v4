package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    private var darkModeEnabled by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            WeatherAppTheme(darkMode = darkModeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(
                        navController = navController,
                        weatherViewModel = weatherViewModel,
                        darkModeEnabled = darkModeEnabled,
                        onToggleDarkMode = { isEnabled ->
                            darkModeEnabled = isEnabled
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    weatherViewModel: WeatherViewModel,
    darkModeEnabled: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    NavHost(navController = navController, startDestination = Screen.WeatherPage.route) {
        composable(route = Screen.WeatherPage.route) {
            WeatherPage(
                viewModel = weatherViewModel,
                navController = navController,
                darkModeEnabled = darkModeEnabled,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable(route = Screen.DetailsPage.route) {
            DetailsPage(viewModel = weatherViewModel, navController = navController)
        }
        composable(route = Screen.SettingsPage.route) {
            SettingsPage(
                navController = navController,
                darkModeEnabled = darkModeEnabled,
                onToggleDarkMode = onToggleDarkMode
            )
        }
    }
}