package com.example.meditationapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.meditationapp.R
import com.example.meditationapp.component.MusicCard

@Composable
fun MusicList(list:List<Int>){
    LazyColumn {
        items(list.size) {
            MusicCard(img = list[it])
        }
    }
}

