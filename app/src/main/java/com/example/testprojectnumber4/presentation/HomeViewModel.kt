package com.example.testprojectnumber4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectnumber4.data.AppRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appRepositoryImpl: AppRepositoryImpl
) : ViewModel() {

    suspend fun logout() = appRepositoryImpl.deleteToken()
    val payments = appRepositoryImpl.payments
    val loadPaymentsError = appRepositoryImpl.loadPaymentsError

    init {
        viewModelScope.launch {
            appRepositoryImpl.loadPayments()
        }
    }
}