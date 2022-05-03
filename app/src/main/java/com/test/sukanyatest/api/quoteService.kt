package com.test.sukanyatest.api

import com.test.sukanyatest.data.models.quotes.quoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface quoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page:Int): Response<quoteList>
}