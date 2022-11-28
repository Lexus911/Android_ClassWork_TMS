package com.example.kotlinapp.listener


interface ItemsListener {

    fun onClick()

    fun onElementSelected(name: String, date: String, imageView: Int )
}