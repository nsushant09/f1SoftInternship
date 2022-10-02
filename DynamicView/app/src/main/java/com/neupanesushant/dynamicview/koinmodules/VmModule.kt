package com.neupanesushant.dynamicview.koinmodules

import com.neupanesushant.dynamicview.viewmodel.FormViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModules() = module {
    viewModel{
        FormViewModel(get())
    }
}