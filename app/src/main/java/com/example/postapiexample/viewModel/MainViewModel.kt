package com.example.quotesapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapiexample.model.response.CustomerListResponse
import com.example.postapiexample.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val repository: UserRepository): ViewModel() {
    init {
        viewModelScope.launch (Dispatchers.IO){
          //  repository.getQuotes()
        }
    }

    val quotes:LiveData<CustomerListResponse>
    get() = repository.quotes


}