package com.neupanesushant.materialdesignlearn

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_CALENDAR
import com.google.android.material.datepicker.MaterialDatePicker.INPUT_MODE_TEXT
import com.neupanesushant.materialdesignlearn.databinding.ActivityPickerBinding
import java.text.SimpleDateFormat

class PickerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPickerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSimpleDatePicker.setOnClickListener {
            showDatePicker()
        }
        binding.btnSimpleDateRangePicker.setOnClickListener {
            showDateRangePicker()
        }
        binding.btnSimpleInputPicker.setOnClickListener {
            showInputPicker()
        }
        binding.btnSimpleCalenderPicker.setOnClickListener {
            showCalenderPicker()
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun showDatePicker(){

        val datePicker = MaterialDatePicker.Builder.datePicker()
//            .setTheme(R.style.DatePickerStyle)
            .setTitleText("Select Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        datePicker.show(supportFragmentManager, "TAG")
        datePicker.addOnPositiveButtonClickListener {
            binding.tvShowSelected.text = SimpleDateFormat("dd/MM/yyyy").format(datePicker.selection).toString()
        }
    }

    fun showDateRangePicker(){

        val dateRangepicker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Select Date Range")
            .build()

        dateRangepicker.show(supportFragmentManager, "TAG")
    }

    @SuppressLint("SimpleDateFormat")
    fun showInputPicker(){

        val inputPicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(INPUT_MODE_TEXT)
            .setTitleText("Write Date")
            .build()

        inputPicker.show(supportFragmentManager, "TAG")
        inputPicker.addOnPositiveButtonClickListener {
            binding.tvShowSelected.text = SimpleDateFormat("dd/MM/yyyy").format(inputPicker.selection).toString()
        }

    }

    fun showCalenderPicker(){
        val calenderPicker = MaterialDatePicker.Builder.datePicker()
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(INPUT_MODE_CALENDAR)
            .setTitleText("Write Date")
            .build()

        calenderPicker.show(supportFragmentManager, "TAG")
        calenderPicker.addOnPositiveButtonClickListener {
            binding.tvShowSelected.text = SimpleDateFormat("dd/MM/yyyy").format(calenderPicker.selection).toString()
        }
    }

}

