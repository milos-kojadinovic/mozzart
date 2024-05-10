package com.testapplication.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.testapplication.App
import com.testapplication.R
import com.testapplication.data.Draw
import com.testapplication.data.Draws
import com.testapplication.ui.getHoursMinutesString
import com.testapplication.ui.screens.BOTTOM_BAR_HEIGHT
import com.testapplication.ui.screens.LoadingSpinner
import com.testapplication.ui.screens.NumberUI
import com.testapplication.ui.theme.AppColors
import com.testapplication.ui.theme.AppTypography

@Composable
fun ResultScreens(resultsViewModel: ResultsViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        resultsViewModel.handleIntent(ResultsIntents.LoadData)
    }

    when (val state = resultsViewModel.state.collectAsState().value) {
        ResultsStates.Error -> TODO()
        is ResultsStates.Loaded -> ResultContent(state.data)
        ResultsStates.Loading -> LoadingSpinner()
    }

}

@Composable
fun ResultContent(results: Draws) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.finished_draws),
            style = AppTypography.headlineMedium
        )
        results.content.forEach {
            DrawItem(it)
        }
        Spacer(modifier = Modifier.height(BOTTOM_BAR_HEIGHT))
    }
}

@Composable
fun DrawItem(draw: Draw) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(8.dp))
            .background(
                AppColors.gameBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.draw_time, draw.getHoursMinutesString()),
        )
        Text(
            modifier = Modifier,
            text = stringResource(R.string.draw_number, draw.drawId),
        )

        draw.winningNumbers.list.chunked(10).forEach { chunk ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                for (number in chunk) {
                    NumberUI(
                        number = number,
                        color = AppColors.selectedButtonColor,
                        isSelected = true,
                        onClick = null
                    )
                }
            }
        }
    }

}
