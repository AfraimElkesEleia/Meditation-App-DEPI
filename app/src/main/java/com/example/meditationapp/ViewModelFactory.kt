package com.example.meditationapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.timertutorial.CountDownViewModel

class ViewModelFactory(private val minutes:Long,private val seconds:Long):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CountDownViewModel::class.java)){
            return CountDownViewModel(minutes,seconds) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }
}