package com.example.testprojectnumber4.data.pojo

import com.google.gson.annotations.SerializedName

data class PaymentsResponse(
    @SerializedName("response") var response: ArrayList<Payment> = arrayListOf()
)