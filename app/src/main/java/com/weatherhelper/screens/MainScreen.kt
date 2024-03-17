@file:OptIn(ExperimentalMaterial3Api::class)

package com.weatherhelper.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weatherhelper.R
import com.weatherhelper.model.Weather
import com.weatherhelper.service.WeatherFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MainScreen(navController: NavController) {
    var city by remember {
        mutableStateOf("")
    }
    var weatherList: List<Weather> by remember {
        mutableStateOf(listOf<Weather>())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
            }
            OutlinedTextField(
                value = city,
                onValueChange = {
                                city = it
                },
                label = { Text("Digite o local...", color = Color.DarkGray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        var call = WeatherFactory().getWeatherService().getWeatherByCity(
                            city = city
                        )
                        call.enqueue(object : Callback<Weather>{
                            override fun onResponse(
                                call: Call<Weather>,
                                response: Response<Weather>
                            ) {
                                val weatherResponse = response.body()
                                weatherResponse?.let { weatherList = listOf(it) }
                                println(weatherList)
                            }
                            override fun onFailure(call: Call<Weather>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })
                    }) {
                    }
                    Icon(imageVector = Icons.Default.Search, contentDescription = "")
                },
                maxLines = 1

            )

        }
        Column (modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 180.dp)){
            WeatherCard(
                colorUser = CardDefaults.cardColors(Color.Transparent),
                path = R.drawable.weather,
                title = "${weatherList.firstOrNull()?.sys?.country ?: "País/Cidade"}",
                content = "${weatherList.firstOrNull()?.main?.temp ?: "0.0"}°C"
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 8.dp)
        ) {
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                WeatherCard(
                    colorUser = CardDefaults.cardColors(Color.Transparent),
                    path = R.drawable.min_temp,
                    title = "Min",
                    content = "${weatherList.firstOrNull()?.main?.tempMin ?: "0.0"}°C"
                )
                WeatherCard(
                    colorUser = CardDefaults.cardColors(Color.Transparent),
                    path = R.drawable.temp_max,
                    title = "Max",
                    content = "${weatherList.firstOrNull()?.main?.tempMax ?: "0.0"}°C"
                )

            }
            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                WeatherCard(
                    colorUser = CardDefaults.cardColors(Color.Transparent),
                    path = R.drawable.humidity_solid,
                    title = "Humidade",
                    content = "${weatherList.firstOrNull()?.main?.humidity ?: "0"}"
                )
                WeatherCard(
                    colorUser = CardDefaults.cardColors(Color.Transparent),
                    path = R.drawable.wind_solid,
                    title = "Vento",
                    content = "${weatherList.firstOrNull()?.wind?.speed ?: "00.00"}Km/h"
                )
            }
        }
    }
}


@Composable
fun WeatherCard(colorUser: CardColors, path: Int, title: String, content: String) {
    Card(
        colors = colorUser,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(25.dp),

    ) {
        Row(modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = path),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
            Column (modifier = Modifier.padding(start = 20.dp)){
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = content)
            }

        }
    }
}