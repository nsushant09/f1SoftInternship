package com.neupanesushant.dynamicview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FormViewModel(val dynamicItemUseCase: DynamicItemUseCase) : ViewModel(){

    private val _itemsList = MutableLiveData<List<DynamicFormItem>>()
    val itemsList : LiveData<List<DynamicFormItem>> get() = _itemsList

    val disposable = CompositeDisposable()

    fun getItemsList() {
        disposable.add(
            dynamicItemUseCase.getDynamicItemsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    _itemsList.value = it
                }
        )

    }

}