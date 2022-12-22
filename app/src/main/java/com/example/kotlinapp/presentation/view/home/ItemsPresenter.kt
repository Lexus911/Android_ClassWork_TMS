package com.example.kotlinapp.presentation.view.home

import com.example.kotlinapp.R
import com.example.kotlinapp.domain.items.ItemsInteractor
import javax.inject.Inject

class ItemsPresenter @Inject constructor(private val itemsInteractor: ItemsInteractor) {

    private lateinit var itemsView: ItemsView

    fun setView(itemsFragment: ItemsFragment){
        itemsView = itemsFragment
    }

    fun getItems(){
        val items = itemsInteractor.getData()
        itemsView.itemsReceived(items)
    }

    fun imageViewClicked(){
        itemsView.imageViewClicked(R.string.imageview_clicked)
    }

    fun itemClicked(name: String, date: String, imageView: Int){
        itemsView.itemCLicked(NavigateWithBundle(name, date, imageView))
    }
}

data class NavigateWithBundle(
    val name: String,
    val date: String,
    val imageView: Int
)