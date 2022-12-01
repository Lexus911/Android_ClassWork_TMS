package com.example.kotlinapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.model.ItemsModel

class ItemsViewModel: ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle>()
    val bundle: LiveData<NavigateWithBundle> = _bundle

    fun getData() {

        val listItems = listOf<ItemsModel>(
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
            ItemsModel(R.drawable.dragon_fruit_max, "Android", "14.01.2023")
        )

        _items.value = listItems
    }

    fun imageViewCLicked(){
        _msg.value = "ImageViewClicked"
    }

    fun elementClicked(name: String, date: String, imageView: Int){
        _bundle.value = NavigateWithBundle(name = name, date = date, image = imageView)
    }
}

data class NavigateWithBundle(
    val image: Int,
    val name: String,
    val date: String
    )
