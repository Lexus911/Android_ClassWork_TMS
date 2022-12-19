package com.example.kotlinapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.presentation.view.home.ItemsViewModel
import com.example.kotlinapp.domain.items.ItemsInteractor

class ItemsViewModelFactory(
    private val itemsInteractor: ItemsInteractor
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}