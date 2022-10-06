package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.EndIconMode
import com.neupanesushant.dynamicview.R

class CustomizedEditText{
    private lateinit var inputLayout : TextInputLayout
    private lateinit var inputEditText : TextInputEditText

    constructor(context: Context, tag : String, hint : String, inputType : Int){
        inputLayout = TextInputLayout(context)
        inputEditText = TextInputEditText(context)

        val inputEditTextParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val inputLayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        inputEditText.tag = tag
        inputEditText.hint = hint
        inputEditText.inputType = inputType

       if(inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD){
           inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
       }
        inputLayout.layoutParams = inputLayoutParams
        inputLayout.setPadding(4, 2,4, 2)
        inputEditText.layoutParams = inputEditTextParams
        inputLayout.addView(inputEditText, inputEditTextParams)
    }

    fun getEditText() : View {
        return inputLayout
    }

}