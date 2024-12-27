package com.example.meditationapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meditationapp.CardModel
import com.example.meditationapp.Constants
import com.example.meditationapp.navigation.MusicScreen
import com.example.meditationapp.R
import com.example.meditationapp.component.CardComponent

var cardsList = listOf(
    CardModel(
        title = "Sleep",
        img = R.drawable.sleep,
        duration = "Duration: 20–60 minutes",
        description = "Guides the mind into a state of relaxation using techniques like body scanning, deep breathing, and calming sounds to reduce stress and prepare the body for rest."
    ),
    CardModel(
        title = "Study",
        img = R.drawable.study,
        duration = "Duration: 10–20 minutes",
        description = "Incorporates mindfulness techniques and ambient sounds to eliminate distractions, improve memory retention, and maintain a productive flow."
    ),CardModel(
        title = "Breathing",
        img = R.drawable.breathing,
        duration = "Duration: 5–15 minutes",
        description = "Guides users to concentrate on their breath, using techniques like diaphragmatic breathing or box breathing to promote a sense of calm and clarity."
    ),CardModel(
        title = "Stress",
        img = R.drawable.stress,
        duration = "Duration: 10–20 minutes",
        description = "Uses affirmations, visualizations, and calming exercises to reduce cortisol levels and promote emotional well-being."
    ),CardModel(
        title = "Morning",
        img = R.drawable.morning,
        duration = "Duration: 10–15 minutes",
        description = "Combines gratitude exercises, positive affirmations, and light stretches to awaken the mind and body."
    ),

)
val list = listOf(
    Constants.listOfSleepImages,
    Constants.listOfStudyImages,
    Constants.listOfBreathingImages,
    Constants.listOfStressImages,
    Constants.listOfMorningImages,
)
@Composable
fun MeditationList(modifier: Modifier = Modifier,navController: NavController) {
    LazyColumn {
        item{
            Text("Choose Your \nMeditation Journey", fontSize = 50.sp, fontWeight = FontWeight.Bold, lineHeight = 50.sp)
        }
        items(cardsList.size) {
            index->
            CardComponent(cardModel = cardsList[index], onTap = {navController.navigate(MusicScreen(list = list[index]))})
        }
    }
}