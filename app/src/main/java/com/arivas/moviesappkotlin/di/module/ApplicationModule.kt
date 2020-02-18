package com.arivas.moviesappkotlin.di.module

import android.app.Application
import android.content.Context
import com.arivas.moviesappkotlin.BuildConfig
import com.arivas.moviesappkotlin.common.API_KEY
import com.arivas.moviesappkotlin.common.db.AppDatabase
import com.arivas.moviesappkotlin.common.db.MoviesDao
import com.arivas.moviesappkotlin.common.network.services.MoviesServices
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepository
import com.arivas.moviesappkotlin.ui.movies.repository.MoviesRepositoryImpl
import com.arivas.moviesappkotlin.ui.movies.viewmodel.MoviesViewModel
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
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
            .addInterceptor { chain ->
                val urlRequest: HttpUrl = chain.request().url()
                val url: HttpUrl = urlRequest.newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()
                val request: Request = chain.request().newBuilder()
                    .url(url)
                    .build()

                return@addInterceptor chain.proceed(request)
            }
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
    fun provideMoviesDao(): MoviesDao {
        return AppDatabase.getInstance(application).moviesDao()
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesServices: MoviesServices, moviesDao: MoviesDao): MoviesRepository {
        return MoviesRepositoryImpl(moviesServices, moviesDao)
    }

    @Provides
    @Singleton
    fun provideMoviesViewModel(moviesRepository: MoviesRepository): MoviesViewModel {
        return MoviesViewModel(moviesRepository)
    }
}