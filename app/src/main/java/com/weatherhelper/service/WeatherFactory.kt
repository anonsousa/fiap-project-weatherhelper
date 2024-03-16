package com.weatherhelper.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherFactory {
    private val url = "https://api.openweathermap.org/data/2.5/"

    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getWeatherService(): WeatherService {
        return retrofitFactory.create(WeatherService::class.java)
    }
}