package com.neupanesushant.dynamicview.data

import com.neupanesushant.dynamicview.api.EndPoints
import com.neupanesushant.dynamicview.data.model.OccupationResponse
import com.neupanesushant.dynamicview.domain.OccupationTypesRepo
import com.neupanesushant.dynamicview.router.RouteCodeConfig
import com.neupanesushant.dynamicview.router.RouteProvider
import io.reactivex.rxjava3.core.Observable

class OccupationTypesRepoImpl(val routeProvider: RouteProvider, val endPoints: EndPoints) : OccupationTypesRepo {
    override fun getOccupationResponse(): Observable<OccupationResponse> {
        return routeProvider.getUrl(RouteCodeConfig.OCCUPTATIONS)
            .flatMap {
                endPoints.getOccupations(it.getUrl())
            }
    }
}