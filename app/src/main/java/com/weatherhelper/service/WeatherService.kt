package com.weatherhelper.service

import com.weatherhelper.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getWeatherByCity(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "f9b499b9ec92837702333f1d127f688b"
    ): Call<Weather>
}