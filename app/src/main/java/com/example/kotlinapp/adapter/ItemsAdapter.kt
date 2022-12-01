package com.example.kotlinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel

class ItemsAdapter(private var itemsListener: ItemsListener):RecyclerView.Adapter<ItemsViewHolder>() {

    private var listItems = listOf<ItemsModel>() // создание листа

    fun submitList(list: List<ItemsModel>){ // инициалзация листа через сеттер
        this.listItems = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fruits, parent, false)
        return ItemsViewHolder(view, itemsListener)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

}