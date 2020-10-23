package com.smaher.covmeter.data.di

import com.google.gson.Gson
import com.smaher.covmeter.BuildConfig
import com.smaher.covmeter.data.network.NewsRemoteService
import com.smaher.covmeter.data.repository.CovidUpdateRemoteRepositoryImpl
import com.smaher.covmeter.domain.repository.CovidUpdateRemoteRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class NetworkModule() {

    private lateinit var newsRetrofitClient: Retrofit.Builder
    private lateinit var countriesRetrofitClient: Retrofit.Builder
    private lateinit var trackingRetrofitClient: Retrofit.Builder

    fun getNewsUpdatesClient(): Retrofit.Builder {
        if (!this::newsRetrofitClient.isInitialized) {
            newsRetrofitClient = provideRetrofitBuilder(
                BuildConfig.NEWS_BASE_URL,
                provideOkHttpClient(),
                provideGson()
            )
        }
        return newsRetrofitClient
    }

    fun getCountriesUpdatesClient(): Retrofit.Builder {
        if (!this::countriesRetrofitClient.isInitialized) {
            countriesRetrofitClient = provideRetrofitBuilder(
                BuildConfig.COUNTRIES_BASE_URL,
                provideOkHttpClient(),
                provideGson()
            )
        }
        return countriesRetrofitClient
    }

    fun getTrackingClient(): Retrofit.Builder {
        if (!this::trackingRetrofitClient.isInitialized) {
            trackingRetrofitClient = provideRetrofitBuilder(
                BuildConfig.TRACKING_BASE_URL,
                provideOkHttpClient(),
                provideGson()
            )
        }
        return trackingRetrofitClient
    }

    private fun provideRetrofitBuilder(
        baseUrl: String,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    private fun provideGson(): Gson {
        return Gson()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

}