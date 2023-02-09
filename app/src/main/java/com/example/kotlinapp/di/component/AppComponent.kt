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
    fun inject(loginFragment: LoginFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(itemsFragment: ItemsFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(searchFragment: SearchFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
}