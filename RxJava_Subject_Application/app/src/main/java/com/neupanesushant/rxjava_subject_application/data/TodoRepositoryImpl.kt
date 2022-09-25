package com.neupanesushant.rxjava_subject_application.data

import com.neupanesushant.rxjava_subject_application.api.EndPoints
import com.neupanesushant.rxjava_subject_application.domain.Todo
import com.neupanesushant.rxjava_subject_application.domain.TodoRepository
import com.neupanesushant.rxjava_subject_application.router.RouteCodeConfig
import com.neupanesushant.rxjava_subject_application.router.RouteProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject
import org.koin.java.KoinJavaComponent.inject

class TodoRepositoryImpl(
    val routeProvider : RouteProvider, val endPoints : EndPoints
    ) : TodoRepository {

    override fun getAllData(): Observable<List<Todo>> {
        return routeProvider.getUrl(RouteCodeConfig.TODOS)
            .flatMap {
                endPoints.getAllData(it.getUrl())
            }

    }

}