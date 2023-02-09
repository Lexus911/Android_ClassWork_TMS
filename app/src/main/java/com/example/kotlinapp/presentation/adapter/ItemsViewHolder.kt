package com.example.kotlinapp.presentation.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel
import com.squareup.picasso.Picasso

class ItemsViewHolder(private val view: View,
                      private var itemsListener: ItemsListener
                      ):RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val description = view.findViewById<TextView>(R.id.tv_name)
        val imageView = view.findViewById<ImageView>(R.id.iv_name)
        val imageDelete = view.findViewById<ImageView>(R.id.iv_delete)
        val favorites = view.findViewById<ImageView>(R.id.btnFav)

        description.text = itemsModel.description

        Picasso.get().load(Uri.parse(itemsModel.image)).into(imageView)

        imageView.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener{
            itemsListener.onElementSelected(
                itemsModel.description,
                itemsModel.image)
        }

        imageDelete.setOnClickListener{
            itemsListener.onDeleteClicked(itemsModel.description)
        }

        favorites.isSelected = itemsModel.isFavorite

        favorites.setOnClickListener{
            favorites.isSelected = !it.isSelected
            itemsListener.onFavClicked(itemsModel.description, it.isSelected)
        }

    }
}