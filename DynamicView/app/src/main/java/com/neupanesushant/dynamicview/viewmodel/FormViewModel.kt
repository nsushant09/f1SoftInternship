package com.neupanesushant.dynamicview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.InputValidation
import com.neupanesushant.dynamicview.data.model.Occupation
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FormViewModel(private val dynamicItemUseCase: DynamicItemUseCase) : ViewModel() {

    private val _itemsList = MutableLiveData<List<DynamicFormItem>>()
    val itemsList: LiveData<List<DynamicFormItem>> get() = _itemsList

    private val _occupationsList = MutableLiveData<List<Occupation>>()
    val occupationsList: LiveData<List<Occupation>> get() = _occupationsList

    private val _viewTagValues = MutableLiveData<HashMap<String, String>>()
    val viewTagValues: LiveData<HashMap<String, String>> get() = _viewTagValues

    private val tempViewTagValues = HashMap<String, String>()

    private val _isViewInputValid = MutableLiveData<HashMap<String, InputValidation>>()
    val isViewInputValid: LiveData<HashMap<String, InputValidation>> get() = _isViewInputValid

    private val tempViewInputValid = HashMap<String, InputValidation>()

    private val _showErrorSnackbar = MutableLiveData<Boolean>()
    val showErrorSnackbar : LiveData<Boolean> get() = _showErrorSnackbar

    private val disposable = CompositeDisposable()

    fun getItemsList() {
        disposable.add(
            dynamicItemUseCase.getDynamicItemsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _itemsList.value = it.dynamicForm
                }, {
                    _showErrorSnackbar.value = true
                })
        )

    }

    fun getOccupationsList(url: String) {
        disposable.add(
            dynamicItemUseCase.getOccupationsList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _occupationsList.value = it
                }

        )
    }

    fun setViewTagValues(key: String, value: String) {
        tempViewTagValues.put(key, value)
        _viewTagValues.value = tempViewTagValues
    }

    fun setViewInputValidation(tag: String, isValid: InputValidation) {
        tempViewInputValid.put(tag, isValid)
        _isViewInputValid.value = tempViewInputValid
    }

    fun setSnackBarError(boolean : Boolean){
        _showErrorSnackbar.value = boolean
    }

}