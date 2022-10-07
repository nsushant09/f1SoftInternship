package com.neupanesushant.dynamicview.domain

import com.neupanesushant.dynamicview.data.model.OccupationResponse
import io.reactivex.rxjava3.core.Observable

interface OccupationTypesRepo {

    fun getOccupationResponse(url : String) : Observable<OccupationResponse>

}