package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import android.widget.Spinner
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.viewmodel.FormViewModel

class CustomizedDropDown {

    private val inputLayout : TextInputLayout
    private val spinner : Spinner

    constructor(context: Context, field : Field){

        inputLayout = TextInputLayout(context, null, com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox_ExposedDropdownMenu)
        spinner = Spinner(context)
        inputLayout.hint = field.label
        spinner.tag = field.tag
        val dropDownItems : List<String> = listOf("Nepal", "India", "Australia", "United Kingdom")
        val dropdownAdapter = ArrayAdapter(context, R.layout.items_list, dropDownItems)
        spinner.adapter = dropdownAdapter
        inputLayout.addView(spinner)
    }

    fun getDropDown() : View {
        return inputLayout
    }


}