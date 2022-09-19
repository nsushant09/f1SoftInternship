package com.neupanesushant.materialdesignlearn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neupanesushant.materialdesignlearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTextFields.setOnClickListener{
            Intent(this, TextFieldsActivity::class.java).apply{
                startActivity(this)
            }
        }

        binding.btnBottomSheetActivity.setOnClickListener {
            Intent(this, BottomSheetActivity::class.java).apply{
                startActivity(this)
            }
        }

        binding.btnDialogActivity.setOnClickListener {
            Intent(this, DialogActivity::class.java).apply{
                startActivity(this)
            }
        }

        binding.btnPickers.setOnClickListener {
            Intent(this, PickerActivity::class.java).apply{
                startActivity(this)
            }
        }


    }
}