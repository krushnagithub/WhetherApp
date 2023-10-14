package com.example.whetherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.whetherapp.model.WeatherResponse
import com.example.whetherapp.network.weatherService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    fun getWeather(cityName: String, apiKey: String): LiveData<WeatherResponse> {
        val weatherData = MutableLiveData<WeatherResponse>()

        val call = weatherService.getWeather(cityName, apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    weatherData.value = response.body()
                } else {
                    val errorResponse = response.errorBody().toString()
                    Log.e("WeatherViewModel", "API Error: $errorResponse")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("WeatherViewModel", "Network Error: ${t.message}")
            }
        })

        return weatherData
    }
}
