package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.DynamicFormResponse
import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.core.Observable

class DynamicItemUseCase(val dynamicItemRepo: DynamicItemRepo, val occupationTypesUseCase: OccupationTypesUseCase) {

    fun getDynamicItemsList() : Observable<DynamicFormResponse> = dynamicItemRepo.getDynamicFormResponse()

    fun getOccupationsList() : Observable<List<Occupation>> = occupationTypesUseCase.getOccupationsList()

}