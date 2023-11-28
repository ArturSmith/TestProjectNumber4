package com.example.testprojectnumber4.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("response")
    @Expose
    val token: Token?
)
