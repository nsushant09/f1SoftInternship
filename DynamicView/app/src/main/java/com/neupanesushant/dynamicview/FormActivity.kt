package com.neupanesushant.dynamicview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.viewModels
import com.neupanesushant.dynamicview.databinding.ActivityFormBinding
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject

class FormActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormBinding
    val viewModel : FormViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        val editText = EditText(this)
//        editText.hint = "First Name"
//
//        binding.layoutFormMain.addView(editText)
        viewModel.getItemsList()
        viewModel.getOccupationsList()

        viewModel.itemsList.observe(this){
            Log.i("TAG", "The list is : $it")
        }

        viewModel.occupationsList.observe(this){
            Log.i("TAG", "The list is : $it")
        }
    }
}