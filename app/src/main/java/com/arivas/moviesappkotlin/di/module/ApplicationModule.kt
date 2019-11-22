package com.arivas.moviesappkotlin.di.module

import android.app.Application
import android.content.Context
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.application.BaseApp
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.interactor.MoviesInteractor
import com.arivas.moviesappkotlin.ui.movies.interactor.MoviesInteractorImpl
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenter
import com.arivas.moviesappkotlin.ui.movies.presenter.MoviesPresenterImpl
import com.arivas.moviesappkotlin.ui.movies.view.MoviesActivity
import com.arivas.moviesappkotlin.ui.movies.view.MoviesView
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesServices {
        return retrofit.create(MoviesServices::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesPresenter(moviesServices: MoviesServices,
                               interactor: MoviesInteractor): MoviesPresenter {
        return MoviesPresenterImpl(moviesServices, interactor)
    }

    @Provides
    @Singleton
    fun provideMoviesInteractor(moviesServices: MoviesServices): MoviesInteractor {
        return MoviesInteractorImpl(moviesServices)
    }

}