package com.neupanesushant.dynamicview

import android.app.Application
import com.neupanesushant.dynamicview.data.Constants
import com.neupanesushant.dynamicview.koinmodules.domainModule
import com.neupanesushant.dynamicview.koinmodules.netModule
import com.neupanesushant.dynamicview.koinmodules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(domainModule(), netModule(Constants.BASE_URL), viewModelModules())
        }
    }
}