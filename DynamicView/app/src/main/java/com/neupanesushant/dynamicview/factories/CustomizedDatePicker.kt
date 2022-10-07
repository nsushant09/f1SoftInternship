package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.viewmodel.FormViewModel

class CustomizedDatePicker {

    private lateinit var datePicker: MaterialDatePicker<Long>
    private lateinit var datePickerButton: MaterialButton

    constructor(context: Context, viewModel: FormViewModel, field: Field) {

        val inputMode: Int
        if (field.inputType.equals("TEXT")) {
            inputMode = INPUT_MODE_TEXT
        } else {
            inputMode = INPUT_MODE_CALENDAR
        }
        val calenderConstraints = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointBackward.now())
            .build()

        datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(inputMode)
            .setTitleText(field.label)
            .setCalendarConstraints(calenderConstraints)
            .build()


        datePickerButton = MaterialButton(context)
        datePickerButton.text = field.label
        datePickerButton.setOnClickListener {
            val activity = context as FragmentActivity
            datePicker.show(activity.supportFragmentManager, "TAG")
        }
    }

    fun getDatePickerButton(): View {
        return datePickerButton
    }

}