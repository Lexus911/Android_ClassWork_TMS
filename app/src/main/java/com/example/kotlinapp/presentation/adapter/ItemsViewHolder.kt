package com.example.kotlinapp.presentation.adapter


import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.databinding.ItemFruitsBinding
import com.example.kotlinapp.presentation.adapter.listener.ItemsListener
import com.example.kotlinapp.model.ItemsModel

class ItemsViewHolder(private val viewBinding: ItemFruitsBinding,
                      private var itemsListener: ItemsListener
                      ):RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(itemsModel: ItemsModel){
        viewBinding.tvName.text = itemsModel.name
        viewBinding.ivName.setBackgroundResource(itemsModel.image)
        viewBinding.tvDate.text = itemsModel.date

        viewBinding.ivName.setOnClickListener{
            itemsListener.onClick()
        }

        itemView.setOnClickListener{
            itemsListener.onElementSelected(itemsModel.name, itemsModel.date, itemsModel.image)
        }
    }
}