package com.neupanesushant.bankingdashboardclone.koinmodules

import com.neupanesushant.bankingdashboardclone.data.RepositoryImpl
import com.neupanesushant.bankingdashboardclone.domain.LastWeekUseCase
import com.neupanesushant.bankingdashboardclone.domain.Repository
import org.koin.dsl.module

fun dataModule() = module{

    single<Repository>{
        RepositoryImpl()
    }


    single{
        LastWeekUseCase(get())
    }
}