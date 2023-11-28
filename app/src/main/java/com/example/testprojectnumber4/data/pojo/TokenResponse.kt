package com.example.testprojectnumber4.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("response")
    @Expose
    val token: Token?
)
