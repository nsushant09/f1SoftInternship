package com.example.retrofit.data

import com.example.retrofit.EndPoints
import com.example.retrofit.domain.Todo
import com.example.retrofit.domain.TodoRepository
import com.example.retrofit.router.RouteCodeConfig
import com.example.retrofit.router.RouteProvider
import io.reactivex.rxjava3.core.Observable
import org.koin.java.KoinJavaComponent.inject

class TodoRepositoryImpl(
//    val routeProvider : RouteProvider, val endPoints : EndPoints
    ) : TodoRepository {

    val routeProvider : RouteProvider by inject(RouteProvider::class.java)
    val endPoints : EndPoints by inject(EndPoints::class.java)

    override fun requestData(): Observable<List<Todo>> {
     return routeProvider.getUrl(RouteCodeConfig.TODOS)
         .take(1)
         .flatMap { route ->
             endPoints.getData(route.getUrl())
         }
    }

}