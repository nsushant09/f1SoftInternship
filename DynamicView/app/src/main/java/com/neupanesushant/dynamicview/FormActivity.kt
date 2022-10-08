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
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.databinding.ActivityFormBinding
import com.neupanesushant.dynamicview.databinding.ActivityMainBinding
import com.neupanesushant.dynamicview.factories.ViewFactory
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject

class FormActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormBinding
    val viewModel : FormViewModel by inject()
    private lateinit var  CODE : String
    private lateinit var btnSubmit : MaterialButton
    private lateinit var fieldsList : List<DynamicFormItem>
    private lateinit var tagValueMapUpdated : HashMap<String, String>
    private var inputString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        CODE = intent.getStringExtra("code")!!
        btnSubmit = MaterialButton(this)
        setContentView(binding.root)

        viewModel.getItemsList()
        viewModel.viewTagValues.observe(this){
            tagValueMapUpdated = it
        }

        viewModel.itemsList.observe(this){
            Log.i("TAG", "Observing teh dynamic form list")
            fieldsList = it
            it.forEach {
                if(it.code == CODE){
                    it.fields.forEach {
                        binding.layoutFormMain.addView(ViewFactory(this,viewModel,it).getView())
                    }
                }
            }
            binding.layoutFormMain.addView(addSubmitButton())
        }


    }

    fun addSubmitButton() : MaterialButton{
        btnSubmit.text = "Submit"
        val btnSubmitParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        btnSubmit.layoutParams = btnSubmitParams
        btnSubmit.setOnClickListener{
            fieldsList.forEach {
                if(it.code == CODE){
                    it.fields.forEach {
                        val value = viewModel.viewTagValues.value?.get(it.tag)
                        val formattedString = "The value of ${it.tag} is : $value \n"
                        inputString += formattedString
                    }
                    Log.i("TAG", inputString)
                    alertDialogShowsInput(inputString)
                }
            }
        }
        return btnSubmit
    }

    fun alertDialogShowsInput(input : String){
        MaterialAlertDialogBuilder(this)
            .setTitle("User Input")
            .setMessage(input)
            .setNeutralButton("OK"){dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


}