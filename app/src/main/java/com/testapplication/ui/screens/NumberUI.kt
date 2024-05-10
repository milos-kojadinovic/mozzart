package com.testapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.testapplication.ui.ifTrueApply
import com.testapplication.ui.theme.AppTypography
import com.testapplication.ui.theme.numberTextStyle

@Composable
fun NumberUI(
    number: Int,
    color: Color,
    isSelected: Boolean,
    onClick: (() -> Unit)?
) {
    Text(
        modifier = Modifier
            .ifTrueApply(!isSelected) {
                shadow(4.dp, shape = CircleShape)
            }
            .size(28.dp)
            .clip(CircleShape)
            .ifTrueApply(onClick != null) {
                clickable { onClick?.invoke() }
            }
            .background(color, shape = CircleShape)
            .wrapContentHeight(),
        textAlign = TextAlign.Center,
        text = number.toString(), style = AppTypography.numberTextStyle
    )
}