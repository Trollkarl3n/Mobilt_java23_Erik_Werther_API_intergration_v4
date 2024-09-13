package com.example.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherapp.API.NetworkResponse

@Composable
fun DetailsPage(viewModel: WeatherViewModel, navController: NavController) {
    val weatherResult = viewModel.weatherResult.observeAsState()

    weatherResult.value?.let { result ->
        when (result) {
            is NetworkResponse.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Details Page", style = MaterialTheme.typography.headlineMedium)
                    // "Go Back" Button
                    Button(
                        onClick = {
                            navController.popBackStack() // Navigates back to the previous screen
                        },
                        modifier = Modifier.align(Alignment.Start)
                    ) {
                        Text(text = "Go Back")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Detailed Weather Information",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Display detailed information
                    Text(text = "Temperature: ${result.data.current.temp_c} °C", fontSize = 20.sp)
                    Text(text = "Wind Speed: ${result.data.current.wind_kph} km/h", fontSize = 20.sp)
                    Text(text = "Humidity: ${result.data.current.humidity}%", fontSize = 20.sp)
                    Text(text = "Precipitation: ${result.data.current.precip_mm} mm", fontSize = 20.sp)
                    Text(text = "UV Index: ${result.data.current.uv}", fontSize = 20.sp)
                    Text(text = "Local Time: ${result.data.location.localtime}", fontSize = 20.sp)

                }
            }
            is NetworkResponse.Error -> {
                Text(text = result.message)
            }
            else -> {}
        }
    }
}