package com.smaher.covmeter.data.repository

import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.data.network.CountriesRemoteService
import com.smaher.covmeter.data.network.NewsRemoteService
import com.smaher.covmeter.data.network.TrackingRemoteService
import com.smaher.covmeter.domain.repository.CovidUpdateRemoteRepository


internal class CovidUpdateRemoteRepositoryImpl constructor(
    private val newsRemoteService: NewsRemoteService?,
    private val countriesRemoteService: CountriesRemoteService,
    private val trackingRemoteService: TrackingRemoteService
) :
    CovidUpdateRemoteRepository {

    override suspend fun getNewsHeadlines(
        apiKey: String?,
        countryCode: String?,
        category: String?
    ): ArticleResponse? {
        return newsRemoteService?.getCountryHeadlines(countryCode, apiKey, category)
    }

    override suspend fun getCountryInfoByName(countryName: String?): List<CountryResponse?> {
        return countriesRemoteService.getCountryByName(countryName)
    }

    override suspend fun getTrackingDataByDateRange(
        dateFrom: String?,
        dateTo: String?
    ): TrackingResponse? {
        return trackingRemoteService.getTrackingDataPerDayRange(dateFrom,dateTo)
    }
}