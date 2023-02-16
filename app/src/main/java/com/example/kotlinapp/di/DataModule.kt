package com.example.kotlinapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.kotlinapp.data.auth.AuthRepositoryImpl
import com.example.kotlinapp.data.items.ItemsRepositoryImpl
import com.example.kotlinapp.data.service.ApiService
import com.example.kotlinapp.data.service.ApiServiceSecond
import com.example.kotlinapp.data.sharedpref.SharedPreferencesHelper
import com.example.kotlinapp.domain.auth.AuthRepository
import com.example.kotlinapp.domain.items.ItemsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
abstract class DataModule {

    @Binds
   abstract fun bindItemsRepository(
        itemsRepositoryImpl: ItemsRepositoryImpl
    ): ItemsRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    companion object{
        private const val SP_KEY = "SP_KEY"
        private const val BASE_URL = "https://api.jsonserve.com"
        private const val BASE_URL_SECOND = "https://jsonplaceholder.typicode.com"


        @Provides
        fun provideSharedPreferences(context: Context): SharedPreferencesHelper{
            return SharedPreferencesHelper(context.getSharedPreferences(SP_KEY, MODE_PRIVATE))
        }

        @Named("First")
        @Provides
        fun provideApiService(@Named("First") retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }

        @Named("First")
        @Provides
        fun provideRetrofitInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        @Named("Second")
        @Provides
        fun provideApiServiceSecond(@Named("Second") retrofit: Retrofit): ApiServiceSecond {
            return retrofit.create(ApiServiceSecond::class.java)
        }

        @Named("Second")
        @Provides
        fun provideRetrofitInstanceSecond(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL_SECOND)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}