package com.neupanesushant.dynamicview.factories

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.google.android.material.textfield.TextInputLayout
import com.neupanesushant.dynamicview.R
import com.neupanesushant.dynamicview.data.model.Field
import com.neupanesushant.dynamicview.data.model.Occupation
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import com.neupanesushant.dynamicview.getLifeCycleOwner
import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.java.KoinJavaComponent.inject
import java.util.*
import kotlin.collections.ArrayList

class CustomizedDropDown {

    private val inputLayout: TextInputLayout
    private val spinner: Spinner
    private lateinit var occupationsList : List<String>

    constructor(context: Context, viewModel: FormViewModel, field: Field) {

        viewModel.getOccupationsList(field.dataUrl)
        inputLayout = TextInputLayout(
            context,
            null,
            com.google.android.material.R.style.Widget_MaterialComponents_TextInputLayout_OutlinedBox_ExposedDropdownMenu
        )
        spinner = Spinner(context)
        inputLayout.hint = field.label
        spinner.tag = field.tag

        val inputLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        inputLayoutParams.topMargin = 8
        inputLayoutParams.bottomMargin = 8

        inputLayout.layoutParams = inputLayoutParams

        viewModel.setViewTagValues(field.tag, "")
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                viewModel.setViewTagValues(field.tag, occupationsList.get(position))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                viewModel.setViewTagValues(field.tag, "")
            }

        }

        context?.getLifeCycleOwner()?.let{
            viewModel.occupationsList.observe(it){
                val dropDownItems: List<String> = getOccupationStringList(it)
                occupationsList = dropDownItems
                val dropdownAdapter = ArrayAdapter(context, R.layout.items_list, dropDownItems)
                spinner.adapter = dropdownAdapter
                inputLayout.addView(spinner)
            }
        }
    }

    fun getDropDown(): View {
        return inputLayout
    }

    fun getOccupationStringList(occupationList : List<Occupation>) : List<String>{
        return occupationList.map {
            it.value
        }
    }

}