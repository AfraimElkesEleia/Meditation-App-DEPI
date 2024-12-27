package com.example.meditationapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class MusicScreen(val list: List<Int>)

@Serializable
object DetTimerScreen

@Serializable
data class TimerScreen(val minutes:Long,val seconds:Long)