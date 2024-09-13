package com.example.weatherapp.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Base URL for the weather API
    private const val baseUrl = "https://api.weatherapi.com"

    // Function to create and return a Retrofit instance
    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl) // Set the base URL for the API
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson to convert JSON responses to Kotlin objects
            .build() // Build and return the Retrofit instance
    }

    // Create an instance of the WeatherAPI interface using Retrofit
    val weatherAPI: WeatherAPI = getInstance().create(WeatherAPI::class.java)
}