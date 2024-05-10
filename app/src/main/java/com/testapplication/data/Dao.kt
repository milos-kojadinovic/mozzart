package com.testapplication.data

data class Game(
    val gameId: Int,
    val drawId: Int,
    val drawTime: Long,
    val status: String,
    val drawBreak: Int,
    val visualDraw: Int,
    val pricePoints: PricePoints,
    val prizeCategories: List<PrizeCategory>,
    val wagerStatistics: WagerStatistics
)

data class PricePoints(
    val addOn: List<AddOn>,
    val amount: Double
)

data class AddOn(
    val id: Int,
    val name: String,
    val price: Double
)

data class PrizeCategory(
    val categoryId: Int,
    val categoryName: String,
    val prizes: List<Prize>
)

data class Prize(
    val prizeAmount: Double,
    val numberOfWinners: Int
)

data class WagerStatistics(
    val totalWagers: Int,
    val totalWinningWagers: Int,
    val totalWinningAmount: Double
)
