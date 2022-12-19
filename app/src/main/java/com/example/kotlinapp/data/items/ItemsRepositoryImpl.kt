package com.example.kotlinapp.data.items

import com.example.kotlinapp.R
import com.example.kotlinapp.domain.items.ItemsRepository
import com.example.kotlinapp.model.ItemsModel
import javax.inject.Inject


class ItemsRepositoryImpl @Inject constructor(): ItemsRepository {

    override fun getData(): List<ItemsModel> {
        return listOf<ItemsModel>(
            ItemsModel(R.drawable.dragon_fruit_max, "Android", "04.11.2023"),
            ItemsModel(R.drawable.blue_banana, "IOS", "05.10.2023"),
            ItemsModel(R.drawable.fruit_1, "C#", "06.09.2023"),
            ItemsModel(R.drawable.lichi, "C++", "07.08.2023"),
            ItemsModel(R.drawable.avokado, "C", "08.07.2023"),
            ItemsModel(R.drawable.ic_launcher_background, "Ruby", "09.06.2023"),
            ItemsModel(R.drawable.ic_launcher_foreground, "Flutter", "10.05.2023"),
            ItemsModel(R.drawable.avokado, "Java", "11.04.2023"),
            ItemsModel(R.drawable.blue_banana, "JavaScript", "12.03.2023"),
            ItemsModel(R.drawable.avokado, "IOS", "13.02.2023"),
            ItemsModel(R.drawable.dragon_fruit_max, "Android", "14.01.2023"))
    }
}