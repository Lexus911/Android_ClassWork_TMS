package com.example.kotlinapp.di

import com.example.kotlinapp.domain.auth.AuthInteractor
import com.example.kotlinapp.domain.items.ItemsInteractor
import com.example.kotlinapp.presentation.view.MainPresenter
import com.example.kotlinapp.presentation.view.auth.LoginPresenter
import com.example.kotlinapp.presentation.view.auth.OnBoardingPresenter
import com.example.kotlinapp.presentation.view.home.DetailsPresenter
import com.example.kotlinapp.presentation.view.home.ItemsPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    fun provideMainPresenter(authInteractor: AuthInteractor): MainPresenter{
        return MainPresenter(authInteractor)
    }

    @Provides
    fun provideLoginPresenter(authInteractor: AuthInteractor): LoginPresenter{
        return LoginPresenter(authInteractor)
    }

    @Provides
    fun provideOnBoardingPresenter(): OnBoardingPresenter{
        return OnBoardingPresenter()
    }

    @Provides
    fun provideItemsPresenter(itemsInteractor: ItemsInteractor): ItemsPresenter{
        return ItemsPresenter(itemsInteractor)
    }

    @Provides
    fun provideDetailsPresenter(authInteractor: AuthInteractor): DetailsPresenter{
        return DetailsPresenter(authInteractor)
    }


}