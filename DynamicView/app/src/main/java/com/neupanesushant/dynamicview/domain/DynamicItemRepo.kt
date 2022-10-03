package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.data.model.DynamicFormResponse
import io.reactivex.rxjava3.core.Observable

interface DynamicItemRepo {

    fun getDynamicFormResponse() : Observable<DynamicFormResponse>

}