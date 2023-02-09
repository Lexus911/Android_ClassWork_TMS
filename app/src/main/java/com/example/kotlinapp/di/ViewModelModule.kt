package com.example.kotlinapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinapp.di.factory.ViewModelFactory
import com.example.kotlinapp.di.factory.ViewModelKey
import com.example.kotlinapp.presentation.view.auth.LoginViewModel
import com.example.kotlinapp.presentation.view.home.HomeViewModel
import com.example.kotlinapp.presentation.view.home.MainActivityViewModel
import com.example.kotlinapp.presentation.view.home.items.DetailsViewModel
import com.example.kotlinapp.presentation.view.home.items.FavoritesViewModel
import com.example.kotlinapp.presentation.view.home.items.ItemsViewModel
import com.example.kotlinapp.presentation.view.home.items.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsViewModel::class)
    abstract fun bindItemsViewModel(viewModel: ItemsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoritesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}