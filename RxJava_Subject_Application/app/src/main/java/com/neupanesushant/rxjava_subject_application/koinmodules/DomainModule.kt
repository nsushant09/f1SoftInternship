package com.neupanesushant.rxjava_subject_application.koinmodules

import com.neupanesushant.rxjava_subject_application.Constants
import com.neupanesushant.rxjava_subject_application.data.TodoRepositoryImpl
import com.neupanesushant.rxjava_subject_application.domain.TodoRepository
import com.neupanesushant.rxjava_subject_application.domain.TodoUseCase
import com.neupanesushant.rxjava_subject_application.router.RouteProvider
import org.koin.dsl.module

fun domainModule() = module {

    single{
        RouteProvider(Constants.BASE_URL)
    }

    single<TodoRepository>{
        TodoRepositoryImpl(get(), get())
    }

    single{
        TodoUseCase(get())
    }




}