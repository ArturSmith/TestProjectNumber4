package com.example.testprojectnumber4.presentation

import androidx.lifecycle.ViewModel
import com.example.testprojectnumber4.data.AppRepositoryImpl

class HomeViewModel(
    private val appRepositoryImpl:AppRepositoryImpl
) : ViewModel() {

    suspend fun deleteToken() = appRepositoryImpl.deleteToken()
}