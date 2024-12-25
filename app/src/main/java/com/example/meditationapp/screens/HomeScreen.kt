package com.example.meditationapp.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meditationapp.R
import com.example.meditationapp.TabModel

val tabItems = listOf(
    TabModel(
        text = "Home",
        selectedIcon = R.drawable.baseline_home_24un,
        unselectedIcon = R.drawable.baseline_home_24
    ),
    TabModel(
        text = "Favourites",
        selectedIcon = R.drawable.baseline_favorite_24,
        unselectedIcon = R.drawable.baseline_favorite_border_24
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var selectedTabItems by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabItems) {
        pagerState.animateScrollToPage(selectedTabItems)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabItems = pagerState.currentPage
    }
    Column(
        modifier = modifier.fillMaxSize().padding(top = 16.dp)
    ) {
        TabRow(selectedTabIndex = selectedTabItems) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabItems, icon = {
                        Icon(
                            painter = painterResource(
                                if (index == selectedTabItems)
                                    item.selectedIcon
                                else
                                    item.unselectedIcon
                            ), contentDescription = null
                        )
                    },
                    onClick = {
                        selectedTabItems = index
                    },
                    text = {
                        Text(text = item.text)
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            MeditationList(modifier = modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
fun TestScreen() {
    HomeScreen()
}