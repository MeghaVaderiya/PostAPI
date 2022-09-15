package com.example.postapiexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.postapiexample.MainActivity
import com.example.postapiexample.model.response.CustomerListResponse
import com.example.uidesign.Model.ApiConfiguration

class UserRepository (private val apiConfiguration: ApiConfiguration){

    val quotesLiveData= MutableLiveData<CustomerListResponse>()

    val quotes: LiveData<CustomerListResponse>
        get()=quotesLiveData

/*    suspend fun getQuotes(page:Int){
        val result = apiConfiguration.getResult()
        if (result?.body()!= null){
            quotesLiveData.postValue(result.body())
        }

    }*/

}