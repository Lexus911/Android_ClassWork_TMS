package com.example.kotlinapp.presentation.view.home

import com.example.kotlinapp.model.ItemsModel

interface ItemsView {

    fun itemsReceived(itemsList : List<ItemsModel>)

    fun imageViewClicked(msg: Int)

    fun itemCLicked(navigationData: NavigateWithBundle)
}