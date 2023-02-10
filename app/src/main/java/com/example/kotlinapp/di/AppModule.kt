package com.example.kotlinapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val application: Application) {

    @Provides
    fun provideApplication():Application = this.application

    @Provides
    fun provideContext(): Context{
        return this.application
    }
}