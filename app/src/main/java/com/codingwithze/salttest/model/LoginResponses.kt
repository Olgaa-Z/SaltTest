package com.codingwithze.salttest.model

import com.google.gson.annotations.SerializedName

data class LoginResponses(
    @SerializedName("token")
    val token : String,
    @SerializedName("error")
    val error: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String

)