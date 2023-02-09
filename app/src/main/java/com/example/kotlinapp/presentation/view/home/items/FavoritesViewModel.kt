package com.example.kotlinapp.presentation.view.home.items

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.model.FavoritesModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavoritesViewModel @Inject constructor(
private val itemsInteractor: ItemsInteractor
): ViewModel() {
    private val _favorites = MutableLiveData<List<FavoritesModel>>()
    val favorites = _favorites



    fun getFavorites(){
        viewModelScope.launch {
            try {
                val favoritesItems = itemsInteractor.getFavorites()
                _favorites.value = favoritesItems
            }catch (e: Exception){
                Log.w("fav error", e.toString())
            }
        }
    }
}