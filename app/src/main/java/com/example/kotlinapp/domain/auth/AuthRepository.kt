package com.example.kotlinapp.domain.auth

import com.example.kotlinapp.model.UserModel

interface AuthRepository {
    suspend fun loginUser(userName: String, userPassword: String)

    suspend fun showUserCreds(): UserModel

    suspend fun doesUserExist(): Boolean

    suspend fun userLogout()
}