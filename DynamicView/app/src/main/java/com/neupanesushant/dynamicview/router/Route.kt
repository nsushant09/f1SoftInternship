package com.neupanesushant.dynamicview.router

class Route {

    private var url: String? = null

    fun getUrl(): String {
        return url!!
    }

    fun setUrl(url: String) {
        this.url = url
    }
}