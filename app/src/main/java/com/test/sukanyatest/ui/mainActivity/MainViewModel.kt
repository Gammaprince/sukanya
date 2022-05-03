package com.test.sukanyatest.ui.mainActivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.sukanyatest.data.models.quotes.quoteList
import com.test.sukanyatest.data.repository.QuoteRepository
import com.test.sukanyatest.data.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository:QuoteRepository): ViewModel() {
//
//    init {
//    viewModelScope.launch(Dispatchers.IO) {
//        repository.getQuotes(1)
//    }
//}



    suspend fun getQuoteApi(){
        repository.getQuotes(1)
    }

    val dataa:LiveData<Response<quoteList>>
    get() = repository.data


}