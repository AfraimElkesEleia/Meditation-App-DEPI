package com.example.meditationapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationapp.CardModel
import com.example.meditationapp.R

@Composable
fun CardComponent(modifier: Modifier = Modifier, cardModel: CardModel,onTap:()->Unit) {
    val pacificoFont = FontFamily(Font(R.font.pacifico_regular, FontWeight.SemiBold))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(225.dp)
            .padding(8.dp).clickable {
               onTap()
            }
    ) {
        Row(modifier = modifier.fillMaxSize().background(Color(0xFF6C48C5)), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(cardModel.img),
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
                    text = cardModel.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
                Text(
                    text = cardModel.description,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 12.sp,
                    fontSize = 10.sp,
                    fontFamily = pacificoFont
                )
                Text(
                    modifier = modifier.padding(vertical = 4.dp),
                    text = cardModel.duration,
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                )
            }
        }
    }
}
