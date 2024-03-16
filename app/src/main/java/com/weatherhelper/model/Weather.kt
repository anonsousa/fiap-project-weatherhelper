package com.weatherhelper.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("weather") val weather: List<WeatherInfo>,
    val main: MainInfo,
    val wind: Wind,
    val clouds: Clouds,
    val sys: Sys,
    val name: String
)

data class WeatherInfo(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainInfo(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Clouds(
    val all: Int
)

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
