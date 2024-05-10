package com.testapplication.ui.screens.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.testapplication.R
import com.testapplication.data.Game
import com.testapplication.ui.getHoursMinsSecondsRemainingString
import com.testapplication.ui.theme.AppTypography
import kotlinx.coroutines.delay

@Composable
fun TimeLeft(modifier: Modifier, game: Game) {
    var timeLeft by remember {
        mutableLongStateOf((game.drawTime - System.currentTimeMillis()))
    }

    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            timeLeft = (game.drawTime - System.currentTimeMillis())
        }
    }

    if (timeLeft > 0)
        Text(modifier = modifier, text = game.getHoursMinsSecondsRemainingString())
    else Text(
        modifier = modifier,
        text = stringResource(R.string.expired),
        style = AppTypography.bodySmall.copy(color = Color.Red, fontWeight = FontWeight(700))
    )
}