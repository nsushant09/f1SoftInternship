package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.DynamicFormResponse
import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.core.Observable

class DynamicItemUseCase(
    private val dynamicItemRepo: DynamicItemRepo,
    private val occupationTypesUseCase: OccupationTypesUseCase
) {

    fun getDynamicItemsList(): Observable<DynamicFormResponse> =
        dynamicItemRepo.getDynamicFormResponse()

    fun getOccupationsList(url: String): Observable<List<Occupation>> =
        occupationTypesUseCase.getOccupationsList(url)

}