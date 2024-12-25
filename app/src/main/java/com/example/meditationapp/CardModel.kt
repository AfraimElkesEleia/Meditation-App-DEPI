package com.example.meditationapp

import androidx.annotation.DrawableRes

data class CardModel(
    @DrawableRes val img:Int,
    val duration:String,
    val description:String,
    val title:String
)