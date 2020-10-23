package com.smaher.covmeter.data.di

import com.google.gson.Gson
import com.smaher.covmeter.data.network.CountriesRemoteService
import com.smaher.covmeter.data.network.NewsRemoteService
import com.smaher.covmeter.data.network.TrackingRemoteService
import com.smaher.covmeter.data.repository.CovidUpdateRemoteRepositoryImpl
import com.smaher.covmeter.domain.repository.CovidUpdateRemoteRepository
import retrofit2.Retrofit


internal class CovidServicesModule() {

    private lateinit var covidUpdatesRemoteRepository: CovidUpdateRemoteRepository
    private lateinit var networkModule: NetworkModule


    private fun provideNewsService(retrofit: Retrofit.Builder): NewsRemoteService {
        return retrofit.build().create(NewsRemoteService::class.java)
    }

    private fun provideCountriesService(retrofit: Retrofit.Builder): CountriesRemoteService {
        return retrofit.build().create(CountriesRemoteService::class.java)
    }

    private fun provideTrackingService(retrofit: Retrofit.Builder): TrackingRemoteService {
        return retrofit.build().create(TrackingRemoteService::class.java)
    }

    private fun provideNewsClient(): NewsRemoteService {
        return provideNewsService(providesNetworkMoudle().getNewsUpdatesClient())
    }

    private fun provideCountriesClient(): CountriesRemoteService {
        return provideCountriesService(providesNetworkMoudle().getCountriesUpdatesClient())
    }

    private fun provideTrackingClient(): TrackingRemoteService {
        return provideTrackingService(providesNetworkMoudle().getTrackingClient())
    }

    private fun providesNetworkMoudle(): NetworkModule {
        if (!this::networkModule.isInitialized) {
            networkModule = NetworkModule()
        }
        return networkModule
    }

    fun getCovidUpdatesRemoteRepository(): CovidUpdateRemoteRepository {
        if (!this::covidUpdatesRemoteRepository.isInitialized) {
            covidUpdatesRemoteRepository =
                CovidUpdateRemoteRepositoryImpl(
                    provideNewsClient(),
                    provideCountriesClient(),
                    provideTrackingClient()
                )
        }
        return covidUpdatesRemoteRepository
    }


}