package com.example.testprojectnumber4.data

import com.example.testprojectnumber4.data.api.ApiFactory
import com.example.testprojectnumber4.data.entity.ScreenState
import com.example.testprojectnumber4.data.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepositoryImpl {
    private val apiFactory = ApiFactory


    suspend fun login(loginRequest: LoginRequest): ScreenState {
        return withContext(Dispatchers.Default) {
            try {
                val response = apiFactory.apiService.login(loginRequest)
                if (response.token!=null){
                    ScreenState.Success
                } else {
                    ScreenState.Error("Wrong login or password, try again.")
                }
            } catch (e: Exception) {
                ScreenState.Error(e.message.toString())
            }
        }
    }


}