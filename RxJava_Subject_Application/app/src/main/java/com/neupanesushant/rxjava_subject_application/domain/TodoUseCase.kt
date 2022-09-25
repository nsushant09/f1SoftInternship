package com.neupanesushant.rxjava_subject_application.domain

import android.util.Log
import com.neupanesushant.rxjava_subject_application.CompletionType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.PublishSubject

class TodoUseCase(
    val todoRepository: TodoRepository
) {

    val disposable = CompositeDisposable()
    private val publishSubject: PublishSubject<List<Todo>> = PublishSubject.create()
    var listOfAllItems: List<Todo> = listOf()

    fun getPublishSubject(completionType: String): PublishSubject<List<Todo>> {

        if (listOfAllItems.size == 0) {
            getAllData(completionType)
        }else{
            setSubjectOnNext(completionType)
        }
        return publishSubject
    }

    fun setSubjectOnNext(completionType: String){
        when (completionType) {
            CompletionType.ALL -> publishSubject.onNext(listOfAllItems)
            CompletionType.COMPLETE -> filterCompleteData()
            CompletionType.INCOMPLETE -> filterInCompleteData()
        }
    }

    private fun getAllData(completionType: String) {
        Log.i("TAG", "Called all data")
        todoRepository.getAllData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                {
                Log.i("TAG", "Throwable")
            },{
                setSubjectOnNext(completionType)
                },
                {
                listOfAllItems = it
            })
    }

    private fun filterCompleteData() {
        Log.i("TAG", "Called complete func")
        disposable.add(
            Observable.just(listOfAllItems)
                .flatMap {
                    Observable.fromIterable(it)
                }
                .filter {
                    it.completed
                }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    publishSubject.onNext(it)
                }

        )
    }

    private fun filterInCompleteData() {
        Log.i("TAG", "Called incomplete func")
        disposable.add(
            Observable.just(listOfAllItems)
                .flatMap {
                    Observable.fromIterable(it)
                }
                .filter {
                    !it.completed
                }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    publishSubject.onNext(it)
                }
        )
    }

//    private fun filterCompleteDataFromStoredList() : List<Todo>{
//        return listOfAllItems.filter {
//            it.completed
//        }
//    }
//
//    private fun filterIncompleteDataFromStoreList() : List<Todo>{
//        return listOfAllItems.filter{
//            !it.completed
//        }
//    }


}