package com.example.kotlinapp.di.component

import com.example.kotlinapp.di.*
import com.example.kotlinapp.di.factory.ScreenScope
import com.example.kotlinapp.presentation.view.auth.LoginFragment
import com.example.kotlinapp.presentation.view.home.HomeFragment
import com.example.kotlinapp.presentation.view.home.MainActivity
import com.example.kotlinapp.presentation.view.home.items.DetailsFragment
import com.example.kotlinapp.presentation.view.home.items.FavoritesFragment
import com.example.kotlinapp.presentation.view.home.items.ItemsFragment
import com.example.kotlinapp.presentation.view.home.items.SearchFragment
import dagger.Component


@Component(
    modules = [
    AppModule::class,
    DataModule::class,
    DomainModule::class,
    DataBaseModule::class,
    ViewModelModule::class
    ]
)

@ScreenScope
interface AppComponent {
    fun inject(fragment: LoginFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: ItemsFragment)
    fun inject(fragment: FavoritesFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: HomeFragment)
    fun inject(activity: MainActivity)
}