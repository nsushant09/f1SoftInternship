package com.neupanesushant.materialdesignlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.neupanesushant.materialdesignlearn.databinding.ActivityTextFieldsBinding

class TextFieldsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityTextFieldsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextFieldsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
             finish()
        }

        val dropDownItems = listOf<String>("Nepal", "India", "Australia", "United Kingdom")
        val dropDownAdapter = ArrayAdapter(baseContext, R.layout.items_list, dropDownItems)
        (binding.textInputDropDown.editText as? AutoCompleteTextView)?.setAdapter(dropDownAdapter)


    }
}