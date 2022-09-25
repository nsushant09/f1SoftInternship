package com.neupanesushant.rxjava_subject_application.router

class Route {

    private var url : String? = null

    fun getUrl() : String {
        return url!!
    }

    fun setUrl(url :String){
        this.url = url
    }
}