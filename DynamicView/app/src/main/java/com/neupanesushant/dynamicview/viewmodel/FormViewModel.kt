package com.neupanesushant.dynamicview.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.Occupation
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class FormViewModel(private val dynamicItemUseCase: DynamicItemUseCase) : ViewModel(){

    private val _itemsList = MutableLiveData<List<DynamicFormItem>>()
    val itemsList : LiveData<List<DynamicFormItem>> get() = _itemsList

    private val _occupationsList = MutableLiveData<List<Occupation>>()
    val occupationsList : LiveData<List<Occupation>> get() = _occupationsList

    private val disposable = CompositeDisposable()

    fun getItemsList() {
        disposable.add(
            dynamicItemUseCase.getDynamicItemsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _itemsList.value = it.dynamicForm
                },{
                    Log.i("TAG", "Error on subscription name : $it")

                })
        )

    }

    fun getOccupationsList(url : String){
        disposable.add(
            dynamicItemUseCase.getOccupationsList(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _occupationsList.value = it
                }

        )
    }

}