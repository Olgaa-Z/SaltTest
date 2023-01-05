package com.codingwithze.salttest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codingwithze.salttest.MainActivity
import com.codingwithze.salttest.R
import com.codingwithze.salttest.databinding.ActivityLoginBinding
import com.codingwithze.salttest.model.ApiResponse
import com.codingwithze.salttest.model.LoginResponses
import com.codingwithze.salttest.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    fun doLogin(){
        val email =binding.txtEmail.text.toString()
        val password = binding.txtPasword.text.toString()

        val viewModel = ViewModelProvider(this).get(TestViewModel::class.java)
        viewModel.callLogin(email, password)
        viewModel.getData().observe(this){

            when(it){
                is ApiResponse.Loading -> {
                    Log.d("Loading: ", it.toString())
                }
                is ApiResponse.Success ->{
                    it.data.let {
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Success, Token : "+it?.token, Toast.LENGTH_SHORT).show()
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