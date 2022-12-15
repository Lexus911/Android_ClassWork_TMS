package com.example.kotlinapp.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel: ViewModel() {
    private val _nav = MutableLiveData<Unit?>()
    val nav: LiveData<Unit?> = _nav

    val onBoardingText = MutableLiveData<String>("default value")

    fun finishButtonCLicked(){
        _nav.value = Unit
    }

    fun finishPerformed(){
        _nav.value = null
    }
}