package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.View
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.viewmodel.FormViewModel

class ViewFactory(
    private val context: Context,
    private val viewModel: FormViewModel,
    private val field: Field
) {

    fun getView(): View {
        Log.i("TAG", "The input type is : ${field.inputType}")
        if (field.inputType.equals("TEXT") or field.inputType.equals("PHONE") or field.inputType.equals(
                "TEXT_PASSWORD"
            )
        ) {
            val inputType: Int
            if (field.inputType.equals("TEXT")) {
                inputType = InputType.TYPE_CLASS_TEXT
            } else if (field.inputType.equals("PHONE")) {
                inputType = InputType.TYPE_CLASS_PHONE
            } else {
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            return CustomizedEditText(context, viewModel, field, inputType).getEditText()

        } else if (field.inputType.equals("DATE")) {
            return CustomizedDatePicker(context, viewModel, field).getView()
        } else if (field.inputType.equals("DROPDOWN")) {
            return CustomizedDropDown(context, viewModel, field).getDropDown()
        } else {
            return View(context)
        }
    }

}