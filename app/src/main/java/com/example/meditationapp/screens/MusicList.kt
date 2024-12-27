package com.example.meditationapp.screens

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import com.example.meditationapp.R
import com.example.meditationapp.component.MusicCard
import com.example.meditationapp.navigation.DetTimerScreenRoute

@Composable
fun MusicList(list: List<Int>,listOfSongs:List<Int>, navController: NavController, title: String) {

    LazyColumn {
        item {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(top = 16.dp),
                text = title,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 50.sp
            )
        }
        items(list.size) {
            MusicCard(img = list[it], navController = navController, onTap = {
                navController.navigate(DetTimerScreenRoute(index = it, listOfSongs = listOfSongs))
            })
        }
    }
}

