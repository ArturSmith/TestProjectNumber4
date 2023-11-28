package com.example.testprojectnumber4.data.api

import com.example.testprojectnumber4.data.pojo.LoginRequest
import com.example.testprojectnumber4.data.pojo.PaymentsResponse
import com.example.testprojectnumber4.data.pojo.TokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): TokenResponse

    @GET("payments")
    suspend fun getPayments(@Header("token") token: String): PaymentsResponse
}




