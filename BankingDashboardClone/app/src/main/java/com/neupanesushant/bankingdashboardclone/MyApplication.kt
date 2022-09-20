package com.neupanesushant.bankingdashboardclone

import android.app.Application
import com.neupanesushant.bankingdashboardclone.koinmodules.dataModule
import com.neupanesushant.bankingdashboardclone.koinmodules.domainModule
import com.neupanesushant.bankingdashboardclone.koinmodules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val listOfModule = listOf(dataModule(), domainModule(), viewModelModule())
        startKoin{
            androidContext(this@MyApplication)
            modules(listOfModule)
        }
    }

}