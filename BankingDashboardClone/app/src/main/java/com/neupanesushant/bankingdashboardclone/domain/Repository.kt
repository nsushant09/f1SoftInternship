package com.neupanesushant.bankingdashboardclone.domain

import io.reactivex.rxjava3.core.Observable
import java.util.*

interface Repository {

    fun getListOfCompanies() : Observable<List<LastWeek>>

}