package com.example.whetherapp.network

import com.example.whetherapp.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): Call<WeatherResponse>
}
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/data/2.5/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val weatherService = retrofit.create(WeatherService::class.java)
