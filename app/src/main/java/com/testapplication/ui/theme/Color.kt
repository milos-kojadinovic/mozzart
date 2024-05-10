package com.testapplication.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppColors {
    val selectedTab = Color(0xFF40E0D0)
    val gameBackground = Color(0xFF7FD5FE)

    val unSelectedTab = Color(0xFFFFFFFF)
    val bottomBarColor: Brush = Brush.verticalGradient(
        Pair(0.0f, Color(0xFF7FD5FE)),
        Pair(1.0f, Color(0xFF30BBFC))
    )
    val bottomBarBackground = Brush.verticalGradient(
        Pair(0.0f, Color(0x00FFFFFF)),
        Pair(1.0f, Color(0xFFFFFFFF))
    )
    val headerBackground = Color(0x2940E0D0)
    val gamesBackground = Color(0x1940E0D0)
    val buttonColor = Color(0xFF40E0D0)
    val selectedButtonColor = Color(0xA0D01010)
}