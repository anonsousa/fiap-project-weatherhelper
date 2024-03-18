package com.weatherhelper.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherhelper.R

@Composable
fun LoginScreen(navController: NavController) {


    Image(
        painter = painterResource(id = R.drawable.test),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds,
    )

    Column(
        modifier = Modifier
            .padding(bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_test),
            contentDescription = "logo",
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
        )
        Text(text = "Weather Helper", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(
            onClick = {
                navController.navigate("main")
            },
            modifier = Modifier
                .padding(top = 150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF855AD3))
        ) {
            Text(
                text = "Pesquisar Clima",
                color = Color.Black
            )
        }
    }
}



