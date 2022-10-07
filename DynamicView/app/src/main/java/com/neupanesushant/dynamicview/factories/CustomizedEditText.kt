package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.data.model.Occupation
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class CustomizedEditText {
    private val inputLayout: TextInputLayout
    private val inputEditText: TextInputEditText

    constructor(context: Context, field : Field, inputType: Int) {
        inputLayout = TextInputLayout(context)
        inputEditText = TextInputEditText(context)

        val inputEditTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val inputLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        inputLayout.layoutParams = inputLayoutParams
        inputLayout.setPadding(4, 2, 4, 2)
        inputEditText.layoutParams = inputEditTextParams

        inputEditText.tag = field.tag
        inputEditText.hint = field.label
        inputEditText.inputType = inputType

        if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
        }

        inputLayout.addView(inputEditText)

    }

    fun getEditText(): View {
        return inputLayout
    }





}