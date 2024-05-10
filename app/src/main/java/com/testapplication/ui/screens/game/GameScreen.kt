package com.testapplication.ui.screens.game

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testapplication.R
import com.testapplication.data.Game
import com.testapplication.ui.getHoursMinutesString
import com.testapplication.ui.screens.LoadingSpinner
import com.testapplication.ui.screens.NumberUI
import com.testapplication.ui.screens.main.TimeLeft
import com.testapplication.ui.theme.AppColors
import com.testapplication.ui.theme.AppTypography
import com.testapplication.ui.theme.header
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        gameViewModel.handleIntent(GameIntents.LoadData)
    }

    when (val state = gameViewModel.state.collectAsState().value) {
        GameStates.Error -> TODO()
        is GameStates.Loaded -> GameLoadedScreen(state.data)
        GameStates.Loading -> LoadingSpinner()
    }


}

@Composable
fun GameLoadedScreen(game: Game) {
    var timeLeft = remember { (game.drawTime - System.currentTimeMillis()) }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            timeLeft = (game.drawTime - System.currentTimeMillis())
        }
    }
    Column(
        Modifier
            .padding(top = 24.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(
                R.string.title,
                game.drawId
            ), style = AppTypography.headlineSmall
        )

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.draw_time, game.getHoursMinutesString()),
        )

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(id = R.string.time_left))

            TimeLeft(modifier = Modifier.padding(start = 8.dp), game = game)
        }

        if (timeLeft > 0) {
            Numbers(Modifier.padding(top = 16.dp))
        }
    }

}

@Composable
fun Numbers(modifier: Modifier = Modifier) {

    val selectedNumbers = remember { mutableStateListOf<Int>() }

    Column(modifier = modifier) {

        Text(text = stringResource(R.string.chosen_numbers), style = AppTypography.header)

        Row(
            Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            selectedNumbers.forEach {
                NumberUI(number = it, color = AppColors.selectedButtonColor, isSelected = true) {
                    selectedNumbers.remove(it)
                }

            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            for (i in 0..7) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (j in 1..10) {
                        val number = (i * 10 + j)

                        val color = if (selectedNumbers.contains(number)) {
                            AppColors.selectedButtonColor
                        } else
                            AppColors.buttonColor

                        val context = LocalContext.current

                        NumberUI(
                            number = number,
                            color = color,
                            isSelected = selectedNumbers.contains(number)
                        ) {
                            when {
                                selectedNumbers.contains(number) -> selectedNumbers.remove(number)
                                !selectedNumbers.contains(number) && selectedNumbers.size == 15 -> {
                                    Toast.makeText(
                                        context,
                                        R.string.max_number_selected,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                else -> selectedNumbers.add(number)
                            }
                        }
                    }

                }
            }

        }
    }

}

