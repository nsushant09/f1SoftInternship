package com.neupanesushant.dynamicview.api

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoints {

    @GET
    fun getDynamicForm(@Url url : String) : Observable<Any>

    @GET
    fun getOccupations(@Url url : String) : Observable<Any>

}