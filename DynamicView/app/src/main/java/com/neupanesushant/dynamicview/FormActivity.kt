package com.neupanesushant.dynamicview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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

//        val materialEditTextLayout : TextInputLayout
//        val materialEditText : TextInputEditText
        viewModel.getItemsList()
        viewModel.getOccupationsList()

        viewModel.itemsList.observe(this){
            Log.i("TAG", "The list is : $it")
        }

        viewModel.occupationsList.observe(this){
            Log.i("TAG", "The occupations list is : $it")
        }
    }

    fun getEditText() : View{
        val inputLayout = TextInputLayout(this)
        val inputEditText = TextInputEditText(this)

        return inputLayout
    }

}