package com.neupanesushant.dynamicview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val viewModel : FormViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this, FormActivity::class.java)
        binding.btnLogin.setOnClickListener {
            startActivity(intent)
        }
        binding.btnRegistration.setOnClickListener {
            startActivity(intent)
        }

    }

}