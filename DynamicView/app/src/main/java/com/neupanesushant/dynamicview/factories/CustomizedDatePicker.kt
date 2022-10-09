package com.neupanesushant.dynamicview.factories

import android.content.Context
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class CustomizedDatePicker {

    private lateinit var datePicker: MaterialDatePicker<Long>
    private lateinit var datePickerButton: MaterialButton
    private val inputLayout: TextInputLayout
    private val inputEditText: TextInputEditText
    private val mViewModel: FormViewModel
    private val mContext: Context
    private val mField: Field

    constructor(context: Context, viewModel: FormViewModel, field: Field) {

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

        inputEditText.inputType = InputType.TYPE_NULL
        inputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
        inputLayout.setEndIconDrawable(R.drawable.ic_baseline_calendar_month_24)
        inputLayout.addView(inputEditText)



        val inputMode: Int
        val formatter = SimpleDateFormat("yyyy/MM/dd")

        if (field.inputType.equals("TEXT")) {
            inputMode = INPUT_MODE_TEXT
        } else {
            inputMode = INPUT_MODE_CALENDAR
        }

        datePicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(inputMode)
            .setTitleText(field.label)
            .setCalendarConstraints(getCalenderConstraints(field.disableFutureDates))
            .setPositiveButtonText("Set Date")
            .build()

        inputEditText.setText(formatter.format(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC))))
        viewModel.setViewTagValues(field.tag, formatter.format(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC))))

        datePicker.addOnPositiveButtonClickListener {
            inputEditText.setText(formatter.format(Date(it)))
            viewModel.setViewTagValues(field.tag, it.toString())
        }
        inputEditText.addTextChangedListener {
            viewModel.setViewTagValues(field.tag, it.toString())
        }

        inputLayout.setEndIconOnClickListener {
            val activity = context as FragmentActivity
            datePicker.show(activity.supportFragmentManager, "TAG")
        }

    }

    private fun getCalenderConstraints(disableFutureDates: Boolean): CalendarConstraints {

        if (disableFutureDates) {
            return CalendarConstraints.Builder()
                .setValidator(DateValidatorPointBackward.now())
                .build()
        }

        return CalendarConstraints.Builder()
            .build()
    }

    fun getView(): View {
        return inputLayout
    }

}