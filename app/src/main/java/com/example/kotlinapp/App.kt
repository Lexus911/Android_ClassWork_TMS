package com.example.kotlinapp

import android.app.Application
import com.example.kotlinapp.di.AppModule
import com.example.kotlinapp.di.component.AppComponent
import com.example.kotlinapp.di.component.DaggerAppComponent


class App : Application(){
    lateinit var appComponent: AppComponent

    fun provideAppComponent(): AppComponent{
       appComponent = DaggerAppComponent
           .builder()
           .appModule(AppModule(this))
           .build()
        return appComponent
    }
}