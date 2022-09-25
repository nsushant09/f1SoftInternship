package com.neupanesushant.rxjava_subject_application.api


import com.neupanesushant.rxjava_subject_application.domain.Todo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoints {

    @GET
    fun getAllData(@Url url : String) : Observable<List<Todo>>

}