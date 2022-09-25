package com.neupanesushant.rxjava_subject_application.koinmodules

import com.neupanesushant.rxjava_subject_application.vm.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun vmModule() = module {
    viewModel<MainViewModel>{
        MainViewModel(get())
    }
}