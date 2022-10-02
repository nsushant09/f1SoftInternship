package com.neupanesushant.dynamicview.data.model

data class Field(
    val dataUrl: String,
    val disableFutureDates: Boolean,
    val inputType: String,
    val label: String,
    val regex: String,
    val required: Boolean,
    val tag: String
)