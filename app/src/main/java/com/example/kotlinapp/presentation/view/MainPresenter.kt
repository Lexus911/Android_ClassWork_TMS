package com.example.kotlinapp.presentation.view

import com.example.kotlinapp.domain.auth.AuthInteractor
import javax.inject.Inject

class MainPresenter @Inject constructor(private val authInteractor: AuthInteractor){

    private lateinit var mainView: MainView

    fun setView(mainActivity: MainActivity){
        mainView = mainActivity
    }

    fun checkUserExists(){
        val doesUserExists = authInteractor.checkUseerExists() // само значение результата
        mainView.userExistsResult(doesUserExists) // передаём активити результат
    }
}