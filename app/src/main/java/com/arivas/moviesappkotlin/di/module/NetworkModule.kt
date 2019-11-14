package com.arivas.moviesappkotlin.di.module

import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMoviesApi(retrofit: Retrofit): MoviesServices {
        return retrofit.create(MoviesServices::class.java)
    }
}