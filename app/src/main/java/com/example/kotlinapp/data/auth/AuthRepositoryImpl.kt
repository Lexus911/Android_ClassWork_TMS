package com.example.kotlinapp.data.auth

import com.example.kotlinapp.data.sharedpref.SharedPreferencesHelper
import com.example.kotlinapp.domain.auth.AuthRepository
import com.example.kotlinapp.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPreferencesHelper: SharedPreferencesHelper
    ): AuthRepository {

    override suspend fun loginUser(userName: String, userPassword: String) {
        withContext(Dispatchers.IO) { // меняем поток с MAIN на IO т.к. запрос в БД делается на IO потоке
            sharedPreferencesHelper.saveUserName(userName)
            sharedPreferencesHelper.saveUserPassword(userPassword)
        }
    }

    override suspend fun showUserCreds(): UserModel {

        return withContext(Dispatchers.IO){ sharedPreferencesHelper.getUserCreds()}
    }

    override suspend fun doesUserExist(): Boolean {
        return withContext(Dispatchers.IO){ sharedPreferencesHelper.checkUserExists()}
    }

    override suspend fun userLogout() {
        withContext(Dispatchers.IO) {
            sharedPreferencesHelper.removeUser()
        }
    }

}