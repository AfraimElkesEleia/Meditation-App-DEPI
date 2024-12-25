package com.example.meditationapp

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes

data class TabModel(
    val text: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int
)