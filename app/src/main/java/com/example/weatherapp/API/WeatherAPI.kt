package com.example.weatherapp.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    // Define a GET request to retrieve current weather data from the API
    @GET("/v1/current.json")
    suspend fun getWeather(
        // Pass the API key as a query parameter
        @Query("key") apikey: String,
        // Pass the city name as a query parameter
        @Query("q") city: String
    ): Response<WeatherModel> // Return the response as a Response object containing a WeatherModel
}