package com.example.kotlinapp.presentation.adapter.listener


interface ItemsListener {

    fun onClick()

    fun onElementSelected(name: String, date: String, imageView: Int )
}