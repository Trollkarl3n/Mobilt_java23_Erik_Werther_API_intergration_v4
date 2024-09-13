package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.API.Constant
import com.example.weatherapp.API.NetworkResponse
import com.example.weatherapp.API.RetrofitInstance
import com.example.weatherapp.API.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherAPI = RetrofitInstance.weatherAPI

    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()

    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    // Function to fetch weather data for a given city
    fun getData(city: String) {

        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                // Make a network call using the weather API
                val response = weatherAPI.getWeather(Constant.apiKey, city)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Could not load data")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Could not load data")
            }
        }
    }
}