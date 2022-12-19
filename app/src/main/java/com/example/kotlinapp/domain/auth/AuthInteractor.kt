package com.example.kotlinapp.domain.auth

import com.example.kotlinapp.model.UserModel
import javax.inject.Inject

class AuthInteractor @Inject constructor(private val authRepository: AuthRepository) {

    fun loginUser(userName: String, userPassword: String){
        authRepository.loginUser(userName, userPassword)
    }

    fun getUserCreds(): UserModel {
        return authRepository.showUserCreds()
    }

    fun checkUseerExists(): Boolean{
        return authRepository.doesUserExist()
    }

    fun logoutUser(){
        authRepository.userLogout()
    }
}