package com.example.kotlinapp.presentation.adapter.listener


interface ItemsListener {

    fun onClick()

    fun onElementSelected(description: String, image: String )
}