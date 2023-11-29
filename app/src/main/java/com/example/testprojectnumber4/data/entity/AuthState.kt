package com.example.testprojectnumber4.data.entity

sealed class AuthState{
    object Authorized:AuthState()
    object NotAuthorized:AuthState()
}
