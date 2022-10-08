package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.viewmodel.FormViewModel

class CustomizedEditText {
    private val inputLayout: TextInputLayout
    private val inputEditText: TextInputEditText

    constructor(context: Context, viewModel: FormViewModel, field: Field, inputType: Int) {
        inputLayout = TextInputLayout(context, null, R.attr.textInputLayoutAttribute)
        inputEditText = TextInputEditText(context)

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

        inputEditText.hint = field.label
        inputEditText.inputType = inputType


        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        }
        inputEditText.addTextChangedListener {
            viewModel.setViewTagValues(field.tag, it.toString())

        }

        viewModel.setViewTagValues(field.tag, "")
        inputLayout.addView(inputEditText)

    }

    fun getEditText(): View {
        return inputLayout
    }


}