package com.neupanesushant.dynamicview.koinmodules

import com.google.gson.GsonBuilder
import com.neupanesushant.dynamicview.api.EndPoints
import okhttp3.Dispatcher
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun netModule(baseUrl: String) = module {
    single {
        GsonBuilder().create()
    }

    single {
        Dispatcher()
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    single<EndPoints> {
        get<Retrofit>().create(EndPoints::class.java)
    }
}