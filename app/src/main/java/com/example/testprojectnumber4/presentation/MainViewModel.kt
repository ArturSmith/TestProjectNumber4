package com.example.testprojectnumber4.presentation

import androidx.lifecycle.ViewModel
import com.example.testprojectnumber4.data.AppRepositoryImpl

class MainViewModel(
    private val appRepositoryImpl: AppRepositoryImpl
) : ViewModel() {

    val authState = appRepositoryImpl.authState

}