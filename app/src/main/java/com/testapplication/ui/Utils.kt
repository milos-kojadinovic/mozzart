package com.testapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.testapplication.data.Draw
import com.testapplication.data.Game
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Modifier.cardModifier(): Modifier =
    this
        .shadow(12.dp, RoundedCornerShape(8.dp))
        .background(Color.White)

fun Game.getHoursMinsSecondsRemainingString(): String {
    val totalSeconds = (this.drawTime - System.currentTimeMillis()) / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    val hoursString = if (hours > 0) hours.getFormattedString() + ":" else ""
    val minutesString = if (minutes > 0 || hours > 0) minutes.getFormattedString() + ":" else ""
    val secondsString = seconds.getFormattedString()

    return hoursString + minutesString + secondsString
}

fun Game.getHoursMinutesString(locale: Locale = Locale.getDefault()): String {
    return getFormattedDate(locale, drawTime)
}

fun Draw.getHoursMinutesString(locale: Locale = Locale.getDefault()): String {
    return getFormattedDate(locale, drawTime)
}

private fun getFormattedDate(locale: Locale, drawTime: Long): String {
    val calendar =
        Calendar.getInstance().apply { timeInMillis = drawTime }
    val dateFormat = SimpleDateFormat("HH:mm", locale)

    return dateFormat.format(calendar.time)
}


fun Long.getFormattedString() = "%02d".format(this)

fun Modifier.ifTrueApply(condition: Boolean, function: Modifier.() -> Modifier) =
    if (condition) function(this) else this