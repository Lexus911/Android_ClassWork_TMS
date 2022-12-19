package com.example.kotlinapp.domain.auth

import com.example.kotlinapp.model.UserModel

interface AuthRepository {
    fun loginUser(userName: String, userPassword: String)

    fun showUserCreds(): UserModel

    fun doesUserExist(): Boolean

    fun userLogout()
}