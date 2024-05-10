package com.testapplication.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testapplication.R
import com.testapplication.data.Game
import com.testapplication.ui.cardModifier
import com.testapplication.ui.theme.AppColors
import com.testapplication.ui.theme.AppTypography
import com.testapplication.ui.theme.header
import com.testapplication.ui.getHoursMinutesString
import com.testapplication.ui.screens.BOTTOM_BAR_HEIGHT
import com.testapplication.ui.screens.LoadingSpinner
import kotlinx.coroutines.delay

@Composable
fun MainScreen(mainViewModel: MainViewModel = hiltViewModel(), navigateNext: (Int) -> Unit) {

    LaunchedEffect(key1 = true) {
        while (true) {
            // Refresh data on screen every 10 seconds
            mainViewModel.handleIntent(MainIntents.LoadData)
            delay(10_000)
        }
    }

    when (val state = mainViewModel.state.collectAsState().value) {
        is MainStates.Loaded -> DataLoaded(state.data, navigateNext)
        MainStates.Loading -> LoadingSpinner()
        MainStates.Error -> TODO()
    }

}

@Composable
fun DataLoaded(data: List<Game>, navigateNext: (Int) -> Unit) {

    Column(
        Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Text(text = " \uD83C\uDDEC\uD83C\uDDF7 Грчки кино", style = AppTypography.headlineMedium)

        Column(
            Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier
                    .padding(vertical = 20.dp)
                    .cardModifier()
            ) {

                Header()

                data.forEach {
                    GameUI(
                        modifier = Modifier
                            .height(48.dp)
                            .clickable { navigateNext(it.drawId) },
                        item = it
                    )
                }

            }
            Spacer(modifier = Modifier.height(BOTTOM_BAR_HEIGHT))

        }
    }
}

@Composable
private fun Header() {
    Column(
        modifier = Modifier
            .background(AppColors.headerBackground)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.drawing), style = AppTypography.header
            )
            Text(text = stringResource(R.string.time_left), style = AppTypography.header)
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))

    }
}

@Composable
fun GameUI(modifier: Modifier, item: Game) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 1.dp)
            .background(AppColors.gamesBackground)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = item.getHoursMinutesString())
        TimeLeft(Modifier.padding(start = 8.dp), item)
    }
}

