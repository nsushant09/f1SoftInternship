package com.neupanesushant.dynamicview

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.InputValidation
import com.neupanesushant.dynamicview.databinding.ActivityFormBinding
import com.neupanesushant.dynamicview.factories.ViewFactory
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    val viewModel: FormViewModel by inject()
    private lateinit var CODE: String
    private lateinit var btnSubmit: MaterialButton
    private lateinit var fieldsList: List<DynamicFormItem>
    private lateinit var tagValueMapUpdated: HashMap<String, String>
    private var inputString = ""
    private var isValid = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        CODE = intent.getStringExtra("code")!!
        btnSubmit = MaterialButton(this)
        supportActionBar?.setTitle(CODE)
        setContentView(binding.root)

        viewModel.getItemsList()
        viewModel.viewTagValues.observe(this) {
            tagValueMapUpdated = it
        }

        viewModel.itemsList.observe(this) {
            binding.progressIndicator.visibility = View.GONE
            fieldsList = it
            it.forEach {
                if (it.code == CODE) {
                    it.fields.forEach {
                        binding.layoutFormMain.addView(ViewFactory(this, viewModel, it).getView())
                    }
                }
            }
            binding.layoutFormMain.addView(addSubmitButton())
        }


    }

    fun addSubmitButton(): MaterialButton {
        btnSubmit.text = "Submit"
        val btnSubmitParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        btnSubmit.layoutParams = btnSubmitParams
        btnSubmit.setOnClickListener {
            fieldsList.forEach {
                if (it.code == CODE) {
                    it.fields.forEach {

                        val value = viewModel.viewTagValues.value?.get(it.tag)

                        if (it.regex != null) {
                            val regex = Regex(it.regex)
                            if (value!!.matches(regex)) {
                                viewModel.setViewInputValidation(it.tag, InputValidation.VALID)
                            } else {
                                viewModel.setViewInputValidation(it.tag, InputValidation.INVALID)
                                isValid = false
                            }
                        }
                        val formattedString = "The value of ${it.tag} is : $value \n"
                        Log.i("TAG", "The value of ${it.tag} is : $value \n")
                        inputString += formattedString
                    }

                    if (isValid) {
//                        alertDialogShowsInput(inputString)
                    }
                    alertDialogShowsInput(inputString)
                }
            }
        }
        return btnSubmit
    }

    fun alertDialogShowsInput(input: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("User Input")
            .setMessage(input)
            .setNeutralButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


}