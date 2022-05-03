package com.test.sukanyatest.data.repository

import com.test.sukanyatest.data.models.quotes.quoteList

sealed class Response<T>(val data:T?=null,val ErrorMessage:String?=null){
    class Loading<T>:Response<T>()
    class Success<T>(data:T?=null):Response<T>(data=data)
    class Error<T>(errorMessage:String):Response<T>(ErrorMessage=errorMessage)
}
