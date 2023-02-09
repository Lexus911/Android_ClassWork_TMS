package com.example.kotlinapp.di

import com.example.kotlinapp.domain.auth.AuthInteractor
import com.example.kotlinapp.domain.auth.AuthRepository
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.domain.items.ItemsRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideItemsInteractor(itemsRepository: ItemsRepository): ItemsInteractor {
        return ItemsInteractor(itemsRepository)
    }

    @Provides
    fun provideAuthInteractor(authRepository: AuthRepository): AuthInteractor {
        return AuthInteractor(authRepository)
    }
}
