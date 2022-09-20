package com.neupanesushant.bankingdashboardclone.koinmodules

import com.neupanesushant.bankingdashboardclone.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewModelModule() = module{

    viewModel{
        HomeViewModel(get())
    }
}