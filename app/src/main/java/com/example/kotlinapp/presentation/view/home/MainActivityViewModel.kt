package com.example.kotlinapp.presentation.view.home


import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import com.example.kotlinapp.R
import com.example.kotlinapp.domain.auth.AuthInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
): ViewModel() {

    private val _nav = MutableLiveData<Int>()
    val nav: LiveData<Int> = _nav

    private val _visibility = MutableLiveData<Int>()
    val visibility: LiveData<Int> = _visibility

    fun checkUserExists(){
        viewModelScope.launch {
           val doesUserExist = authInteractor.checkUseerExists()
            _nav.value = when(doesUserExist) {
                true -> R.navigation.main_graph
                false -> R.navigation.auth_graph
            }
        }
    }

    fun destinationChanged(destination: NavDestination){
       if(destination.id == R.id.loginFragment || destination.id == R.id.homeFragment  ){
           _visibility.value = GONE
        }else{
           _visibility.value = VISIBLE
        }
    }
}