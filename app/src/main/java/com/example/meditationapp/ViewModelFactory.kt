package com.example.meditationapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timertutorial.CountDownViewModel

class ViewModelFactory(
    private val minutes: Long,
    private val seconds: Long,
    val context: Context,
    val listOfSongs: List<Int>,
    val index: Int
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountDownViewModel::class.java)) {
            return CountDownViewModel(
                minutes,
                seconds,
                listOfSongs = listOfSongs,
                context = context,
                index = index
            ) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }
}