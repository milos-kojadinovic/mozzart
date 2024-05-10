package com.testapplication.data

import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Repository(
    private val apiServiceApi: ServiceApi
) {


    suspend fun getUpcoming(): Response<List<Game>> {
        return apiServiceApi.getUpcoming()
    }

    suspend fun getData(drawId: Int): Response<Game> {
        return apiServiceApi.getInfo(drawId)
    }

    suspend fun getResults(): Response<Draws> {
        val currentTime = System.currentTimeMillis()
        val lastDay = currentTime - (24 * 60 * 60 * 1000)

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val fromDate = dateFormatter.format(Date(lastDay))
        val toDate = dateFormatter.format(Date(currentTime))// Not working for today
        return apiServiceApi.getResults(
            fromDate,
            fromDate
        ) // Past three hours, add pagination in future
    }

}