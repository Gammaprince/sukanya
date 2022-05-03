package com.test.sukanyatest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object retrofitHelper {
    const val BASE_URL="https://quotable.io/"

    fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}