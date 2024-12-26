package com.example.meditationapp

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class MusicScreen(val list: List<Int>)