package com.neupanesushant.dynamicview.data

import com.neupanesushant.dynamicview.api.EndPoints
import com.neupanesushant.dynamicview.data.model.DynamicFormItem
import com.neupanesushant.dynamicview.domain.DynamicItemRepo
import com.neupanesushant.dynamicview.router.RouteCodeConfig
import com.neupanesushant.dynamicview.router.RouteProvider
import io.reactivex.rxjava3.core.Observable

class DynamicItemRepoImpl(val routeProvider: RouteProvider, val endPoints: EndPoints) : DynamicItemRepo {

    override fun getDynamicItemsList(): Observable<List<DynamicFormItem>> {

        return routeProvider.getUrl(RouteCodeConfig.DYNAMIC_FORM)
            .flatMap {
                endPoints.getDynamicForm(it.getUrl())
            }

    }

}