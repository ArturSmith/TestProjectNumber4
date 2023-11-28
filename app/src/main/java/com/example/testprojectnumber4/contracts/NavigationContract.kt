package com.example.testprojectnumber4.contracts

import androidx.fragment.app.Fragment

interface NavigationContract {
    fun navigateToHomeScreen()
    fun navigateToLoginScreen()
}

fun Fragment.navigation(): NavigationContract {
    return requireActivity() as NavigationContract
}