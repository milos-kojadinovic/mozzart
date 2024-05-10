package com.testapplication.data


data class Draws(
    val content: List<Draw>
)

data class Draw(
    val gameId: Int,
    val drawId: Long,
    val drawTime: Long,
    val status: String,
    val drawBreak: Int,
    val visualDraw: Long,
    val pricePoints: PricePoints,
    val winningNumbers: WinningNumbers
)


data class WinningNumbers(
    val list: List<Int>,
)