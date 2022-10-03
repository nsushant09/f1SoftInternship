package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.core.Observable

class OccupationTypesUseCase(val occupationTypesRepo: OccupationTypesRepo) {

    fun getOccupationsList(): Observable<List<Occupation>> =
        occupationTypesRepo.getOccupationResponse()
            .map {
                it.occupationTypes
            }

}