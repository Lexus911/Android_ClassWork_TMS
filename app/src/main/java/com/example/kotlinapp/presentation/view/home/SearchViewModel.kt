package com.example.kotlinapp.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.model.ItemsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val itemsInteractor: ItemsInteractor
): ViewModel() {

    private val _item = MutableLiveData<ItemsModel>()
    val item: LiveData<ItemsModel> = _item

    fun findItem(searchText: String){
        viewModelScope.launch {
            val foundItem = itemsInteractor.findItem(searchText)
            _item.value = foundItem
        }
    }

}