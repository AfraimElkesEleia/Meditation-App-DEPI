package com.example.meditationapp.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditationapp.R
var isClicked by  mutableStateOf(false)
@Composable
fun MusicCard(modifier: Modifier = Modifier,@DrawableRes img:Int) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(220.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(6f),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(R.drawable.baseline_favorite_border_24),
                contentDescription = null,
                modifier=Modifier.fillMaxSize().weight(1f)
            )
        }
    }
}
