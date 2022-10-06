package com.neupanesushant.dynamicview

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.core.view.marginTop
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.databinding.ActivityFormBinding
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding
import com.neupanesushant.dynamicview.factories.ViewFactory
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject

class FormActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormBinding
    val viewModel : FormViewModel by inject()
    private lateinit var  CODE : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        CODE = intent.getStringExtra("code")!!
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
            Log.i("TAG", "Observing teh dynamic form list")
            it.forEach {
                if(it.code == CODE){
                    it.fields.forEach {
                        binding.layoutFormMain.addView(ViewFactory(this, it).getView())
                    }
                }
            }
        }

        viewModel.occupationsList.observe(this){
            Log.i("TAG", "The occupations list is : $it")
        }
    }

}