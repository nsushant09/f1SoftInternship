package com.neupanesushant.dynamicview.factories

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.neupanesushant.dynamicview.FormActivity
import com.neupanesushant.dynamicview.data.model.Field

class ViewFactory(private val context : Context ,private val field : Field) {

    fun getView() : View{
        Log.i("TAG", "The input type is : ${field.inputType}")
        if(field.inputType.equals("TEXT") or  field.inputType.equals("PHONE") or field.inputType.equals("TEXT_PASSWORD")){
            val inputType : Int
            if(field.inputType.equals("TEXT")){
                inputType = InputType.TYPE_CLASS_TEXT
            }else if (field.inputType.equals("PHONE")){
                inputType = InputType.TYPE_CLASS_PHONE
            }else{
                inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            return CustomizedEditText(context, field.tag, field.label,inputType).getEditText()

        }else if(field.inputType.equals("DATE")){
            return CustomizedDatePicker(context, field).getDatePickerButton()
        }else if(field.inputType.equals("DROPDOWN")){
            return View(context)
        }else{
            return View(context)
        }
    }

}