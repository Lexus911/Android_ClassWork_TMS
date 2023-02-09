package com.example.kotlinapp.presentation.view.home.items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.auth.AuthInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailsViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav


    fun logoutUser(){
        viewModelScope.launch {
            authInteractor.logoutUser()
            _nav.value = R.navigation.auth_graph
        }
    }


}