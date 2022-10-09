package com.neupanesushant.dynamicview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neupanesushant.dynamicview.data.Constants
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this, FormActivity::class.java)
        binding.btnLogin.setOnClickListener {
            intent.putExtra("code", Constants.USER_LOGIN_CODE)
            startActivity(intent)
        }
        binding.btnRegistration.setOnClickListener {
            intent.putExtra("code", Constants.USER_REGISTRATION_CODE)
            startActivity(intent)
        }

    }

}