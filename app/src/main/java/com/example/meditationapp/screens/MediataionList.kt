package com.example.meditationapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.meditationapp.component.CardComponent

@Composable
fun MeditationList(modifier: Modifier = Modifier) {
    LazyColumn{
        items(count = 6) {
            CardComponent()
        }
    }
}