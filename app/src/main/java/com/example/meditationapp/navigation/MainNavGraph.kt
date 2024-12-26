package com.example.meditationapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.meditationapp.CustomNavType
import com.example.meditationapp.MainScreen
import com.example.meditationapp.MusicScreen
import com.example.meditationapp.component.MusicCard
import com.example.meditationapp.screens.HomeScreen
import com.example.meditationapp.screens.MusicList
import kotlin.reflect.typeOf


@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = MainScreen
    ){
        composable<MainScreen> {
            HomeScreen(navController = navController)
        }
        composable<MusicScreen> (
            typeMap = mapOf(
                typeOf<List<Int>>() to CustomNavType.ListType
            )
        ){
            val args = it.toRoute<MusicScreen>()
            MusicList(args.list)
        }
    }
}