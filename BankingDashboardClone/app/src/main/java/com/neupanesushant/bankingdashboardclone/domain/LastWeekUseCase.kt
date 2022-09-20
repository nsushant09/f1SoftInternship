package com.neupanesushant.bankingdashboardclone.domain

import io.reactivex.rxjava3.core.Observable

class LastWeekUseCase(private val repository: Repository) {

    fun getListOfCompanies() : Observable<List<LastWeek>> = repository.getListOfCompanies()

}