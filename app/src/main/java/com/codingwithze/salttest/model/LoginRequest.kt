package com.codingwithze.salttest.model

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    val loginResponse : LoginResponses
)