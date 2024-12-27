package com.example.meditationapp.navigation

import androidx.media3.exoplayer.ExoPlayer
import kotlinx.serialization.Serializable

@Serializable
object MainScreen

@Serializable
data class MusicScreen(val list: List<Int>,val listOfSong:List<Int>,val title:String)

@Serializable
data class DetTimerScreenRoute(val index:Int,val listOfSongs:List<Int>)

@Serializable
data class TimerScreen(val minutes:Long,val seconds:Long,val index:Int,val listOfSongs: List<Int>)