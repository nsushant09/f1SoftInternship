package com.neupanesushant.bankingdashboardclone.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neupanesushant.bankingdashboardclone.data.RepositoryImpl
import com.neupanesushant.bankingdashboardclone.domain.LastWeek
import com.neupanesushant.bankingdashboardclone.domain.LastWeekUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.schedulers.SchedulerPoolFactory
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(val lastWeekUseCase: LastWeekUseCase): ViewModel() {

    private val _listOfCompanies = MutableLiveData<List<LastWeek>>()
    val listOfCompanies get() : LiveData<List<LastWeek>> = _listOfCompanies

    val disposables = CompositeDisposable()
    init{

    }

    fun getListOfCompanies(){
        disposables.add(
            lastWeekUseCase.getListOfCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ responseList ->
                    _listOfCompanies.value = responseList
                },{throwable ->
                    Log.i("HomeViewModel", "Throwable error : $throwable")
                })
        )
    }

}