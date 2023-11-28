package com.example.testprojectnumber4.data.pojo

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("amount") var amount: String? = null,
    @SerializedName("created") var created: String? = null
)
