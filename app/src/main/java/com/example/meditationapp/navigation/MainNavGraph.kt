package com.example.meditationapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.meditationapp.screens.DetTimerScreen
import com.example.meditationapp.screens.HomeScreen
import com.example.meditationapp.screens.MusicList
import com.example.timertutorial.TimerScreen
import kotlin.reflect.typeOf


@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ) {
        composable<MainScreen> {
            HomeScreen(navController = navController)
        }
        composable<MusicScreen>(
            typeMap = mapOf(
                typeOf<List<Int>>() to CustomNavType.ListType
            )
        ) {
            val args = it.toRoute<MusicScreen>()
            MusicList(args.list, args.listOfSong, navController, args.title)
        }
        composable<DetTimerScreenRoute>(
            typeMap = mapOf(
                typeOf<List<Int>>() to CustomNavType.ListType
            )
        ) {
            val args = it.toRoute<DetTimerScreenRoute>()
            DetTimerScreen(
                navController = navController,
                index = args.index,
                listOfSongs = args.listOfSongs
            )
        }
        composable<TimerScreen>(
            typeMap = mapOf(
                typeOf<List<Int>>() to CustomNavType.ListType
            )
        ) {
            val args = it.toRoute<TimerScreen>()
            Box(contentAlignment = Alignment.Center) {
                TimerScreen(
                    minutes = args.minutes,
                    seconds = args.seconds,
                    index = args.index,
                    listOfSongs = args.listOfSongs
                )
            }
        }
    }
}