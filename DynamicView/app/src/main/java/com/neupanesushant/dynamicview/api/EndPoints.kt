package com.neupanesushant.dynamicview.api

import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.DynamicFormResponse
import com.neupanesushant.dynamicview.data.model.Occupation
import com.neupanesushant.dynamicview.data.model.OccupationResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface EndPoints {

    @GET
    fun getDynamicForm(@Url url : String) : Observable<DynamicFormResponse>

    @GET
    fun getOccupations(@Url url : String) : Observable<OccupationResponse>

}