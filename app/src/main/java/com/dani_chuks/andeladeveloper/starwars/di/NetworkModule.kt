package com.dani_chuks.andeladeveloper.starwars.di


import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import com.dani_chuks.andeladeveloper.starwars.data.db.remote.ApiService
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.MainConnectivityManager
import com.dani_chuks.andeladeveloper.starwars.di.qualifiers.TestConnectivityManager
import com.dani_chuks.andeladeveloper.starwars.util.ConnectivityLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesApiService(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
                .baseUrl("https://swapi.co/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @SuppressLint("VisibleForTests")
    @Provides
    @TestConnectivityManager
    @Singleton
    fun provideConnectivityLiveData(connectivityManager: ConnectivityManager): ConnectivityLiveData {
        return ConnectivityLiveData(connectivityManager)
    }

    @Provides
    @MainConnectivityManager
    @Singleton
    fun provideConnectivityLiveDataFromContext(context: Context): ConnectivityLiveData {
        return ConnectivityLiveData(context)
    }
}
