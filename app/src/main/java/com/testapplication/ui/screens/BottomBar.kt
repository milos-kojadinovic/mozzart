package com.testapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.testapplication.ui.theme.AppColors


 val BOTTOM_BAR_HEIGHT = 72.dp

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedScreen: String,
    selectedScreenChanged: (BottomBarRoutes) -> Unit
) {
    Box(
        modifier = modifier
            .background(AppColors.bottomBarBackground)
            .height(BOTTOM_BAR_HEIGHT)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .padding(bottom = 18.dp)
                .background(
                    brush = AppColors.bottomBarColor,
                    RoundedCornerShape(50)
                )
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            BottomBarRoutes.entries.forEach {
                BottomBarItem(
                    bottomBarItem = it,
                    isSelected = selectedScreen == it.name,
                    onSelected = selectedScreenChanged
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    bottomBarItem: BottomBarRoutes,
    isSelected: Boolean,
    onSelected: (BottomBarRoutes) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .clickable { onSelected(bottomBarItem) }
                .background(if (isSelected) AppColors.selectedTab else AppColors.unSelectedTab),
            painter = painterResource(id = bottomBarItem.iconId),
            contentDescription = null,
            contentScale = ContentScale.Inside,
        )
    }
}
