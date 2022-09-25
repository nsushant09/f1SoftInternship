package com.neupanesushant.rxjava_subject_application

import android.app.Application
import com.neupanesushant.rxjava_subject_application.koinmodules.domainModule
import com.neupanesushant.rxjava_subject_application.koinmodules.netModule
import com.neupanesushant.rxjava_subject_application.koinmodules.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(domainModule(), netModule(Constants.BASE_URL), vmModule())
        }
    }

}