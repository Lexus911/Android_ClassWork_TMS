package com.example.kotlinapp.presentation.view.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.model.ItemsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
): ViewModel() {

    private val _items = MutableLiveData<List<ItemsModel>>()
    val items: LiveData<List<ItemsModel>> = _items

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getData() {
        viewModelScope.launch {
            try {
                itemsInteractor.getData()
                val listItems = itemsInteractor.showData()
                _items.value = listItems
            }catch (e: Exception){
                _error.value = e.message.toString() //обычно передаётся просто строка
            }

        }
    }

    fun imageViewCLicked(){
        _msg.value = R.string.imageview_clicked
    }

    fun elementClicked(description: String, image: String){
        _bundle.value = NavigateWithBundle(description, image, destinationId = R.id.action_itemsFragment_to_detailsFragment)
    }

    fun userNavigated(){
        _bundle.value = null
    }

    fun deleteItem(description: String){
        viewModelScope.launch {
            itemsInteractor.deleteItemByDescription(description)
        }
}
}

data class NavigateWithBundle(
    val description: String,
    val image: String,
    val destinationId: Int
    )
