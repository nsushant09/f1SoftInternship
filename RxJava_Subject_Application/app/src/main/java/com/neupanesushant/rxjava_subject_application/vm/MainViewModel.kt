package com.neupanesushant.rxjava_subject_application.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neupanesushant.rxjava_subject_application.CompletionType
import com.neupanesushant.rxjava_subject_application.domain.Todo
import com.neupanesushant.rxjava_subject_application.domain.TodoUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(
    val todoUseCase: TodoUseCase
) : ViewModel() {

    private var _selectedItem = MutableLiveData<String>()

    private var _selectedItemList = MutableLiveData<List<Todo>>()
    val selectedItemList : LiveData<List<Todo>> get() = _selectedItemList

    fun setSelectedItemType( selectedType : String){
        _selectedItem.value = selectedType
        getSelectedItemList(selectedType)
    }

    fun setChangedSelectedItemType( selectedType : String){
        _selectedItem.value = selectedType
        todoUseCase.setSubjectOnNext(selectedType)
    }

    private fun getSelectedItemList(selectedType : String){
        setItemsToList(selectedType)
    }

    private fun setItemsToList(selectedType: String){
        todoUseCase.getPublishSubject(selectedType)
            .subscribe {
                Log.i("TAG", "Size of list in vm : ${it.size}")
                _selectedItemList.value = it
            }
    }

}