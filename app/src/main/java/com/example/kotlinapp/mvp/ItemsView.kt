package com.example.kotlinapp.mvp

import com.example.kotlinapp.model.ItemsModel

interface ItemsView {

    fun dataReceived(list: List<ItemsModel>)

    fun imageViewClicked(msg: Int)


    fun goToDetails(name: String, date: String, imageView: Int)
}