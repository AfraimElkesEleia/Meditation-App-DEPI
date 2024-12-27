package com.example.timertutorial

import android.content.Context
import android.net.Uri
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.timertutorial.TimeFormat.timeFormat
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class CountDownViewModel(
    private val minutes: Long,
    private val seconds: Long,
    val context: Context,
    val listOfSongs: List<Int>,
    val index: Int,
) : ViewModel() {
  private val player: ExoPlayer
    init {
        player = ExoPlayer.Builder(context).build()
        listOfSongs.forEach { index ->
            val path = "android.resource://" + context.packageName + "/" + index
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
        }
        player.prepare()
        whichMusic(index)
    }

    private var countDownTimer: CountDownTimer? = null
    private val userInputMinute = TimeUnit.MINUTES.toMillis(minutes)
    private val userInputSeconds = TimeUnit.SECONDS.toMillis(seconds)
    private var currentIndex by mutableIntStateOf(index)
    val totalTime = userInputSeconds + userInputMinute
    var timeLeft by mutableLongStateOf(totalTime)
    var timerText = mutableStateOf(timeLeft.timeFormat())
    val countDownInterval = 1000L //every one second
    var isPlaying by mutableStateOf(false)
    var isFinished by mutableStateOf(false)
    fun startCountDownTimer() = viewModelScope.launch {
        isPlaying = true
        player.play()
        countDownTimer = object : CountDownTimer(timeLeft, countDownInterval) {
            override fun onTick(currentTimeLeft: Long) {
                timerText.value = currentTimeLeft.timeFormat()
                timeLeft = currentTimeLeft
            }

            override fun onFinish() {
                Log.d("tag", "Done")
                timerText.value = totalTime.timeFormat()
                isPlaying = false
                isFinished = true
                player.pause()
                whichMusic(currentIndex)
            }
        }.start()
    }

    fun stopCountDownTimer() = viewModelScope.launch {
        isPlaying = false
        countDownTimer?.cancel()
        player.pause()
    }

    fun resetCountDownTimer() = viewModelScope.launch {
        isPlaying = false
        countDownTimer?.cancel()
        timerText.value = totalTime.timeFormat()
        timeLeft = totalTime
        isFinished = false
        player.pause()
        whichMusic(index = currentIndex)
    }
    fun whichMusic(index: Int){
        player.seekToDefaultPosition(index)
    }
    fun exitFromScreen(){
        player.stop()
    }
    fun goToNext(){
        player.seekToNextMediaItem()
        currentIndex = player.currentMediaItemIndex
    }
    fun goToPrev(){
        player.seekToPrevious()
        currentIndex = player.currentMediaItemIndex
    }
}
