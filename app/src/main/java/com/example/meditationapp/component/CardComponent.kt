package com.example.meditationapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.R

@Composable
fun CardComponent(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(225.dp).padding(8.dp)
    ) {
        Row(modifier = modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.study),
                contentDescription = null,
                modifier = modifier
                    .height(200.dp)
                    .width(200.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Sleep",
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                )
                Text(
                    text = "Guides the mind into a state of relaxation using techniques like body scanning, deep breathing, and calming sounds to reduce stress and prepare the body for rest.",
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 12.sp,
                    fontSize = 10.sp,
                )
                Text(
                    modifier = modifier.padding(vertical = 4.dp),
                    text = "Duration: 20–60 minutes",
                    fontWeight = FontWeight.Bold,
                    fontSize = 6.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun CardTest() {
    CardComponent()
}