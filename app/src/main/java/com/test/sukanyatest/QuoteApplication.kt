package com.test.sukanyatest

import android.app.Application
import com.test.sukanyatest.api.quoteService
import com.test.sukanyatest.api.retrofitHelper
import com.test.sukanyatest.data.db.QuoteDatabase
import com.test.sukanyatest.data.repository.QuoteRepository
import com.test.sukanyatest.ui.mainActivity.MainActivity

class QuoteApplication:Application() {
    lateinit var quoteRespository:QuoteRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        val database = QuoteDatabase.getDatabase(applicationContext)
        // Instantiating quoteService implemented by Retrofit
        val quoteService = retrofitHelper.getInstance().create(quoteService::class.java)

        //Instantiating repository
        val quoteRespository=QuoteRepository(quoteService,applicationContext,database)



    }
}