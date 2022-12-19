package com.example.kotlinapp.data.sharedpref

import android.content.SharedPreferences
import com.example.kotlinapp.model.UserModel
import javax.inject.Inject

class SharedPreferencesHelper @Inject constructor(private val sharedPreferences: SharedPreferences){
    fun saveUserName(name: String?){
        sharedPreferences.edit().putString("USER_NAME", name).apply()
    }

    fun saveUserPassword(password: String?){
        sharedPreferences.edit().putString("USER_PASSWORD", password).apply()
    }

    fun getUserCreds(): UserModel{
        val name = sharedPreferences.getString(USER_NAME,"") ?: "No user"
        val password = sharedPreferences.getString(USER_PASSWORD,"") ?: "No user"
        return UserModel(name, password)
    }

    fun checkUserExists(): Boolean{
        val name = sharedPreferences.getString(USER_NAME,"")
        val password = sharedPreferences.getString(USER_PASSWORD,"")
        return (!name.isNullOrEmpty() && !password.isNullOrEmpty())
    }

    fun removeUser(){
        saveUserName(null)
        saveUserPassword(null)
    }

    companion object{
        private const val USER_NAME = "USER_NAME"
        private const val USER_PASSWORD = "USER_PASSWORD"
    }
}