package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.Occupation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class OccupationTypesUseCase(private val occupationTypesRepo: OccupationTypesRepo) {

    fun getOccupationsList(url : String) : Observable<List<Occupation>>{
        return occupationTypesRepo.getOccupationResponse(url)
            .map {
                it.occupationTypes
            }
    }

}