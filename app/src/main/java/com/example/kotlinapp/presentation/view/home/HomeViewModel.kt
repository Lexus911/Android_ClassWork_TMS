package com.example.kotlinapp.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.auth.AuthInteractor
import com.example.kotlinapp.model.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _userCreds = MutableLiveData<UserModel>()
    val userCreds: LiveData<UserModel> = _userCreds

    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav


    fun showUserData(){
        viewModelScope.launch {
            _userCreds.value = authInteractor.getUserCreds()
        }

    }

    fun toRecyclerView(){
        _nav.value = R.navigation.main_graph
    }


}