package com.example.kotlinapp.data.auth

import com.example.kotlinapp.data.sharedpref.SharedPreferencesHelper
import com.example.kotlinapp.domain.auth.AuthRepository
import com.example.kotlinapp.model.UserModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val sharedPreferencesHelper: SharedPreferencesHelper): AuthRepository {
    override fun loginUser(userName: String, userPassword: String) {
        sharedPreferencesHelper.saveUserName(userName)
        sharedPreferencesHelper.saveUserPassword(userPassword)
    }

    override fun showUserCreds(): UserModel {
        return sharedPreferencesHelper.getUserCreds()
    }

    override fun doesUserExist(): Boolean {
        return sharedPreferencesHelper.checkUserExists()
    }

    override fun userLogout() {
        return sharedPreferencesHelper.removeUser()
    }

}