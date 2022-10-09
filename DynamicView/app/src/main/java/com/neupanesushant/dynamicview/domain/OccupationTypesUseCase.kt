package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.core.Observable

class OccupationTypesUseCase(private val occupationTypesRepo: OccupationTypesRepo) {

    fun getOccupationsList(url : String) : Observable<List<Occupation>>{
        return occupationTypesRepo.getOccupationResponse(url)
            .map {
                it.occupationTypes
            }
    }

}