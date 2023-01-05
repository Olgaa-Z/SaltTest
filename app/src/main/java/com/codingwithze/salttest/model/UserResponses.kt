package com.codingwithze.salttest.model


import com.google.gson.annotations.SerializedName

data class UserResponses(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("support")
    val support: Support
)