package com.example.testprojectnumber4.data.entity

sealed class ScreenState() {
    object Initial : ScreenState()
    object Loading : ScreenState()
    object Success : ScreenState()
    data class Error(val errorMessage:String) : ScreenState()
}
