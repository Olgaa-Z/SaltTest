package com.codingwithze.salttest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.codingwithze.salttest.databinding.ActivityMainBinding
import com.codingwithze.salttest.model.ApiResponse
import com.codingwithze.salttest.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        profilUser()
    }

    fun profilUser(){
        val viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        viewModel.callUser()
        viewModel.getDataUser().observe(this){

            when(it){
                is ApiResponse.Loading -> {
                    Log.d("Loading: ", it.toString())
                }
                is ApiResponse.Success ->{
                    it.data.let {
                        Glide.with(this).load(it?.data?.avatar).into(binding.imgUser)
                        binding.firstName.text = it?.data?.firstName
                        binding.lastName.text = it?.data?.lastName
                        binding.emailUser.text = it?.data?.email
                    }
                }
                is ApiResponse.Error -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                    Log.d("Error: ", it.toString())


                }
            }
        }
    }
}