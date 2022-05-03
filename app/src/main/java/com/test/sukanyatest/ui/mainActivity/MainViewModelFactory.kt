package com.test.sukanyatest.ui.mainActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.sukanyatest.data.repository.QuoteRepository

class MainViewModelFactory(private val repo:QuoteRepository,private val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repo) as T
    }
}