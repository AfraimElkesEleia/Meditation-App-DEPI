package com.example.meditationapp.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditationapp.navigation.DetTimerScreen

var isClicked by mutableStateOf(false)

@Composable
fun MusicCard(modifier: Modifier = Modifier, @DrawableRes img: Int, navController: NavController) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(220.dp)
            .clickable {
                navController.navigate(DetTimerScreen)
            }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
