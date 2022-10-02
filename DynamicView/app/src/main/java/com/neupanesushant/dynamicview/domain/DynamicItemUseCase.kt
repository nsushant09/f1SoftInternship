package com.neupanesushant.dynamicview.domain

class DynamicItemUseCase(val dynamicItemRepo: DynamicItemRepo) {

    fun getDynamicItemsList() = dynamicItemRepo.getDynamicItemsList()

}