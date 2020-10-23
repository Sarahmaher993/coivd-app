package com.smaher.covmeter.data.repository

import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.domain.repository.CovidUpdateRemoteRepository
import com.smaher.covmeter.domain.repository.CovidUpdatesRepository


internal class CovidUpdatesRepositoryImpl constructor(private val covidUpdatesRepository: CovidUpdateRemoteRepository) :
    CovidUpdatesRepository {

    override suspend fun getNewsHeadlines(
        apiKey: String?,
        countryCode: String?,
        category: String?
    ): ArticleResponse? {
        return covidUpdatesRepository.getNewsHeadlines(apiKey, countryCode, category)
    }

    override suspend fun getCountryInfoByName(countryname: String?): List<CountryResponse?> {
        return covidUpdatesRepository.getCountryInfoByName(countryname)
    }

    override suspend fun getTrackingDataByDateRange(
        dateFrom: String?,
        dateTo: String?
    ): TrackingResponse? {
        return covidUpdatesRepository.getTrackingDataByDateRange(dateFrom,dateTo)
    }
}