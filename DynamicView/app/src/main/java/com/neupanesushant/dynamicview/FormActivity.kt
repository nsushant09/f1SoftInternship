package com.neupanesushant.dynamicview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.neupanesushant.dynamicview.databinding.ActivityFormBinding
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val editText = EditText(this)
//        editText.hint = "First Name"
//
//        binding.layoutFormMain.addView(editText)
    }
}