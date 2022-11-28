package com.example.kotlinapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.R
import com.example.kotlinapp.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel

class ItemsViewHolder(private val view: View,
                      private var itemsListener: ItemsListener
                      ):RecyclerView.ViewHolder(view) {

    fun bind(itemsModel: ItemsModel){
        val name = view.findViewById<TextView>(R.id.tv_name)
        val imageView = view.findViewById<ImageView>(R.id.iv_name)
        val date = view.findViewById<TextView>(R.id.tv_date)

        name.text = itemsModel.name
        imageView.setBackgroundResource(itemsModel.image)
        date.text = itemsModel.date

        imageView.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener{
            itemsListener.onElementSelected(itemsModel.name, itemsModel.date, itemsModel.image)
        }
    }
}