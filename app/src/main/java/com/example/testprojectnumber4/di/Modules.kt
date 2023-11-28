package com.example.testprojectnumber4.di

import com.example.testprojectnumber4.data.AppRepositoryImpl
import com.example.testprojectnumber4.data.api.ApiFactory
import com.example.testprojectnumber4.presentation.HomeViewModel
import com.example.testprojectnumber4.presentation.LoginViewModel
import com.example.testprojectnumber4.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { ApiFactory }
    single { AppRepositoryImpl(get(), get()) }
    viewModelOf(::MainViewModel)
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

