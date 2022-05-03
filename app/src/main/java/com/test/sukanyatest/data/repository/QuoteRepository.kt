package com.test.sukanyatest.data.repository

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.sukanyatest.api.quoteService
import com.test.sukanyatest.data.db.QuoteDatabase
import com.test.sukanyatest.data.models.quotes.Result
import com.test.sukanyatest.data.models.quotes.quoteList
import com.test.sukanyatest.ui.mainActivity.MainActivity
import java.lang.Exception

class QuoteRepository(private val quoteS:quoteService,private val context:Context,private val quotedb:QuoteDatabase) {

    private val mutableData= MutableLiveData<Response<quoteList>>()

    val data:LiveData<Response<quoteList>>
    get() = mutableData

    suspend fun getQuotes(page:Int){
        if(isOnline(context)){
            val result=quoteS.getQuotes(page)
            try {
                if(result.body() !=null){
                    quotedb.quoteDao().addQuotes(result.body()!!.results)
                    mutableData.postValue(Response.Success(result.body()))

                }
            }
            catch (e : Exception){
                mutableData.postValue(Response.Error(e.toString()))



            }
        }
        else{
            Toast.makeText(context,"You are offline",Toast.LENGTH_LONG).show()
        }


    }
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}