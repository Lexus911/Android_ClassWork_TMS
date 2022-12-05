package com.example.kotlinapp.presentation

import com.example.kotlinapp.R
import com.example.kotlinapp.domain.ItemsInteractor
import com.example.kotlinapp.model.ItemsModel

class ItemsPresenter(private val itemsView: ItemsView,
                     private val itemsInteractor: ItemsInteractor) {



    fun getData(){
        val listItems = itemsInteractor.getData()
        itemsView.dataReceived(listItems)
    }

    fun imageViewClicked(){
        itemsView.imageViewClicked(R.string.imageview_clicked)
    }

    fun elementSelected(name: String, date: String, imageView: Int){
        itemsView.goToDetails(name, date, imageView)
    }
}