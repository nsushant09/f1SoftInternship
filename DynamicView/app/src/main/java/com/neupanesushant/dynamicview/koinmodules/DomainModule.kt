package com.neupanesushant.dynamicview.koinmodules

import com.neupanesushant.dynamicview.data.Constants
import com.neupanesushant.dynamicview.data.DynamicItemRepoImpl
import com.neupanesushant.dynamicview.data.OccupationTypesRepoImpl
import com.neupanesushant.dynamicview.domain.DynamicItemRepo
import com.neupanesushant.dynamicview.domain.DynamicItemUseCase
import com.neupanesushant.dynamicview.domain.OccupationTypesRepo
import com.neupanesushant.dynamicview.domain.OccupationTypesUseCase
import com.neupanesushant.dynamicview.router.RouteProvider
import org.koin.dsl.module

fun domainModule() = module {
    single {
        RouteProvider(Constants.BASE_URL)
    }

    single<DynamicItemRepo> {
        DynamicItemRepoImpl(get(), get())
    }

    single<OccupationTypesRepo> {
        OccupationTypesRepoImpl(get(), get())
    }

    single {
        OccupationTypesUseCase(get())
    }
    single<DynamicItemUseCase> {
        DynamicItemUseCase(get(), get())
    }


}