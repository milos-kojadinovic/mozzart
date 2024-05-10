package com.testapplication.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ServiceApi {

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("upcoming/{pageSize}")
    suspend fun getUpcoming(
        @Path("pageSize") pageSize: Int = 20
    ): Response<List<Game>>

    @Headers("Content-type: application/json; charset=utf-8")
    @GET("{drawId}")
    suspend fun getInfo( @Path("drawId") drawId: Int): Response<Game>

    @GET("draw-date/{fromDate}/{toDate}")
    suspend fun getResults(
        @Path("fromDate") fromDate: String,
        @Path("toDate") toDate: String
    ): Response<Draws>
}
