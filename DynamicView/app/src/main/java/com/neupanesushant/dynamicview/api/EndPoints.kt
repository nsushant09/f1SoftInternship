package com.neupanesushant.dynamicview.api

import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoints {

    @GET
    fun getDynamicForm(@Url url : String) : Observable<List<DynamicFormItem>>

    @GET
    fun getOccupations(@Url url : String) : Observable<Occupation>

}