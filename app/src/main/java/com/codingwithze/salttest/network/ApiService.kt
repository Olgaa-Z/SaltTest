package com.codingwithze.salttest.network

import com.codingwithze.salttest.model.Data
import com.codingwithze.salttest.model.LoginRequest
import com.codingwithze.salttest.model.LoginResponses
import com.codingwithze.salttest.model.UserResponses
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("api/login")
    @FormUrlEncoded
    fun postLogin(
        @Field("email") email : String?,
        @Field("password") password : String?
    ): Call<LoginResponses>

    @GET("api/users/2")
    fun getProfil():Call<UserResponses>

//    @POST("api/login")
//    @FormUrlEncoded
//    fun postLogin(
//        @Field("email") email : String?,
//       @Field("password") password : String?
//    ): LoginResponses
}