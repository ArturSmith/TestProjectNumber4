package com.example.testprojectnumber4.data.api

import com.example.testprojectnumber4.data.models.LoginRequest
import com.example.testprojectnumber4.data.models.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response
}




