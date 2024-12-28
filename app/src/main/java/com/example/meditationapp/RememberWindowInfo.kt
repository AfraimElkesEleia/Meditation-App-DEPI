package com.example.meditationapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowInfo {
    val localConfiguration = LocalConfiguration.current
    return WindowInfo(
        screenWidthInfo = when {
            localConfiguration.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
            localConfiguration.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo =when {
            localConfiguration.screenWidthDp < 480 -> WindowInfo.WindowType.Compact
            localConfiguration.screenWidthDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeight = localConfiguration.screenWidthDp.dp,
        screenWidth = localConfiguration.screenHeightDp.dp
    )
}

data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp,
) {
    sealed class WindowType {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}