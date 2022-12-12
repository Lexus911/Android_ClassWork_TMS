package com.example.kotlinapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.presentation.view.ItemsViewModel
import com.example.kotlinapp.domain.ItemsInteractor

class ItemsViewModelFactory(
    private val itemsInteractor: ItemsInteractor
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemsViewModel(itemsInteractor) as T
    }
}