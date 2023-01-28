package com.example.kotlinapp.presentation.view.home.items.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.databinding.ItemFavoritesBinding
import com.example.kotlinapp.model.FavoritesModel
import com.example.kotlinapp.model.ItemsModel
import com.example.kotlinapp.presentation.adapter.ItemsViewHolder
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener

class FavoritesAdapter(

): RecyclerView.Adapter<FavoritesViewHolder>() {

    private var listItems = listOf<FavoritesModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<FavoritesModel>){

        this.listItems = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val viewBinding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoritesViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

}