package com.example.kotlinapp.presentation.view.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinapp.R

class OnBoardingViewModel: ViewModel() {
    private val _nav = MutableLiveData<NavToItems?>()
    val nav: LiveData<NavToItems?> = _nav

    val onBoardingText = MutableLiveData<String>("On boarding fragment")

    fun finishButtonCLicked(){
        _nav.value = NavToItems(R.id.action_onBoardingFragment2_to_itemsFragment, R.id.onBoardingFragment2)
    }

    fun finishPerformed(){
        _nav.value = null
    }
}

data class NavToItems(
    val destinationId: Int,
    val removeFragmentId: Int
)