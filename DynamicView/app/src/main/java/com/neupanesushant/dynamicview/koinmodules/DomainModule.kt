package com.neupanesushant.dynamicview.koinmodules

import com.neupanesushant.dynamicview.data.Constants
import com.neupanesushant.dynamicview.router.RouteProvider
import org.koin.dsl.module

fun domainModule() = module{
    single{
        RouteProvider(Constants.BASE_URL)
    }
}