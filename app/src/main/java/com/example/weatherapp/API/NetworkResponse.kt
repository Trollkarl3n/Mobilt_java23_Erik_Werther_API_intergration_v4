package com.example.weatherapp.API

// A sealed class to represent different states of a network request
sealed class NetworkResponse<out T> {

    // Represents a successful network response, with the response data
    data class Success<out T>(val data: T) : NetworkResponse<T>()

    data class Error(val message: String) : NetworkResponse<Nothing>()

    object Loading : NetworkResponse<Nothing>()
}