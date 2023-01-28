package com.example.kotlinapp.presentation.view.home.items.adapter


import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.databinding.ItemFavoritesBinding
import com.example.kotlinapp.model.FavoritesModel
import com.squareup.picasso.Picasso


class FavoritesViewHolder(
    private val viewBinding: ItemFavoritesBinding,
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(favItems: FavoritesModel) {

        viewBinding.tvName.text = favItems.description
        Picasso.get().load(Uri.parse(favItems.image)).into(viewBinding.ivName)
    }
}