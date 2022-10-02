package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import io.reactivex.rxjava3.core.Observable

interface DynamicItemRepo {

    fun getDynamicItemsList() : Observable<List<DynamicFormItem>>

}