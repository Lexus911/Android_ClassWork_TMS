package com.example.kotlinapp.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.domain.auth.AuthInteractor
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {
    private val _nav = MutableLiveData<Unit?>()
    val nav: LiveData<Unit?> = _nav


    fun logoutUser(){
        authInteractor.logoutUser()
        _nav.value = Unit
    }


}