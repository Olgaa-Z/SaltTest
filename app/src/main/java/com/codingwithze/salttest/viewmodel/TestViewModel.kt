package com.codingwithze.salttest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingwithze.salttest.model.*
import com.codingwithze.salttest.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(var api : ApiService) : ViewModel() {

    lateinit var liveDataTest : MutableLiveData<ApiResponse<LoginResponses>>
    lateinit var liveDataUser : MutableLiveData<ApiResponse<UserResponses>>


    init {
        liveDataTest = MutableLiveData()
        liveDataUser = MutableLiveData()
    }

    fun getData(): MutableLiveData<ApiResponse<LoginResponses>>{
        return  liveDataTest
    }

    fun getDataUser(): MutableLiveData<ApiResponse<UserResponses>>{
        return  liveDataUser
    }

    fun callUser(){
        api.getProfil()
            .enqueue(object : Callback<UserResponses>{
                override fun onResponse(
                    call: Call<UserResponses>,
                    response: Response<UserResponses>
                ) {
                    if (response.isSuccessful){
                        val data = response.body()

                        data?.let {
                            liveDataUser.postValue(ApiResponse.Success(it))
                        }
                    }else{
                        try {
                            response.errorBody()?.let {
                                val jsonObject = JSONObject(it.string()).getString("error")
                                liveDataUser.postValue(ApiResponse.Error(jsonObject))
                            }
                        } catch (e: Exception) {
                            liveDataUser.postValue(ApiResponse.Error("${e.message}"))
                        }
                    }
                }

                override fun onFailure(call: Call<UserResponses>, t: Throwable) {
                    liveDataUser.postValue(null)
                }

            })

    }

    fun callLogin(email : String, password : String){
        api.postLogin(email,password)
            .enqueue(object : Callback<LoginResponses>{
                override fun onResponse(
                    call: Call<LoginResponses>,
                    response: Response<LoginResponses>
                ) {
                    if (response.isSuccessful){
//                        liveDataTest.postValue(response.body())
                        val data = response.body()

                        data?.let {
                            liveDataTest.postValue(ApiResponse.Success(it))
                        }
                    }else{
//                        liveDataTest.postValue(error(response.body()!!.error))
//                        liveDataTest.postValue(response.body())
                        try {
                            response.errorBody()?.let {
                                val jsonObject = JSONObject(it.string()).getString("error")
                                liveDataTest.postValue(ApiResponse.Error(jsonObject))
                            }
                        } catch (e: Exception) {
                            liveDataTest.postValue(ApiResponse.Error("${e.message}"))
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponses>, t: Throwable) {
                    liveDataTest.postValue(error(call.toString()))
                }

            })
    }
}