package com.example.kotlinapp.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val _main = MutableLiveData<Unit?>()
    val main: LiveData<Unit?> = _main

    fun showAll(){
        _main.value = Unit
    }
}