package com.example.kotlinapp.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {
    private val _details = MutableLiveData<Unit?>()
    val details: LiveData<Unit?> = _details

    fun showDetails(){
        _details.value = Unit
    }
}