package com.example.meditationapp.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.meditationapp.R
import com.example.meditationapp.navigation.TimerScreen

val textStyle = TextStyle(
    fontSize = 120.sp,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    color = Color.Black
)

@Composable
fun DetTimerScreen(modifier: Modifier = Modifier, navController: NavController ,index:Int,listOfSongs:List<Int>) {
    var textMinutes by remember { mutableStateOf("") }
    var textSeconds by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Determine Session Time",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp,
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(painter = painterResource(R.drawable.timer), contentDescription = null)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                placeholder = {
                    Text(
                        "00", fontSize = 120.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                },
                value = textMinutes,
                onValueChange = {
                    try {
                        if (it.length <= 2 && it.toLong() <= 60) textMinutes = it
                    } catch (e: NumberFormatException) {
                        textMinutes = ""
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                textStyle = textStyle,
                modifier = Modifier.width(180.dp)
            )
            Text(
                text = ":",
                fontSize = 120.sp,
                textAlign = TextAlign.Center,
            )
            TextField(
                placeholder = {
                    Text(
                        "00", fontSize = 120.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                },
                value = textSeconds,
                onValueChange = {
                    try {
                        if (it.length <= 2 && it.toLong() <= 59) textSeconds = it
                    } catch (e: NumberFormatException) {
                        textSeconds = ""
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                textStyle = textStyle,
                modifier = Modifier.width(180.dp)
            )
        }
        Button(onClick = {
            if(textMinutes.isEmpty())
                textMinutes = "00"
            if(textSeconds.isEmpty())
                textSeconds = "00"
            navController.navigate(TimerScreen(textMinutes.toLong(), textSeconds.toLong(),index = index, listOfSongs))
        }) {
            Text("Lets Start")
        }
    }
}

