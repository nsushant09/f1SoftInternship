package com.neupanesushant.rxjava_subject_application.domain

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject

interface TodoRepository {

    fun getAllData() : Observable<List<Todo>>

}