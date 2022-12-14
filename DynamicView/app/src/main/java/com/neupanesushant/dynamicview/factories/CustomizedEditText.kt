package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.data.model.InputValidation
import com.neupanesushant.dynamicview.getLifeCycleOwner
import com.neupanesushant.dynamicview.viewmodel.FormViewModel

class CustomizedEditText {
    private val inputLayout: TextInputLayout
    private val inputEditText: TextInputEditText
    private val mViewModel: FormViewModel
    private val mContext: Context
    private val mField: Field

    constructor(context: Context, viewModel: FormViewModel, field: Field, inputType: Int) {
        inputLayout = TextInputLayout(context)

        //pass inputlayout context to achieve material design results.
        inputEditText = TextInputEditText(inputLayout.context)
        mContext = context
        mViewModel = viewModel
        mField = field

        val inputEditTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val inputLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        inputLayoutParams.topMargin = 8
        inputLayoutParams.bottomMargin = 8

        inputLayout.layoutParams = inputLayoutParams
        inputLayout.setPadding(4, 2, 4, 2)
        inputEditText.layoutParams = inputEditTextParams

        if (field.required) {
            inputEditText.hint = field.label + " *"
        } else {
            inputEditText.hint = field.label
        }

        inputEditText.inputType = inputType


        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            inputEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        }
        inputEditText.addTextChangedListener {
            viewModel.setViewTagValues(field.tag, it.toString())
            viewModel.setViewInputValidation(field.tag, InputValidation.VALID)
            inputLayout.isErrorEnabled = false
        }


        viewModel.setViewTagValues(field.tag, "")
        checkInvalid()
        inputLayout.addView(inputEditText)

    }

    fun getEditText(): View {
        return inputLayout
    }

    fun checkInvalid() {
        mContext?.getLifeCycleOwner()?.let {
            mViewModel.isViewInputValid.observe(it) {
                if (it.get(mField.tag) == InputValidation.INVALID) {
                    inputLayout.isErrorEnabled = true
                    inputLayout.error = "Invalid Input"
                }
            }
        }
    }


}