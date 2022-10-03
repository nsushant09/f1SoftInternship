package com.neupanesushant.dynamicview.router

import io.reactivex.rxjava3.core.Observable

class RouteProvider(val mBaseUrl : String) {

    private val routes : MutableMap<String, String> = HashMap<String, String>()
    init{
        initializeRoutes()
    }

    private fun initializeRoutes(){
        routes.put(RouteCodeConfig.OCCUPTATIONS, "sr8B98VD")
        routes.put(RouteCodeConfig.DYNAMIC_FORM, "07XcFbHm")
    }

    fun getUrl(routeCode : String) :Observable<Route>{
        val route = Route()

        if(routes.contains(routeCode)){
            route.setUrl(mBaseUrl + routes.get(routeCode))
        }else{
            throw IllegalArgumentException("Route not defined")
        }

        return Observable.just(route)
    }



}