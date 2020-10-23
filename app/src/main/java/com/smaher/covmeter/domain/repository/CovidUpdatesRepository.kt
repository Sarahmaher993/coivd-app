package com.smaher.covmeter.domain.repository

import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse

interface CovidUpdatesRepository {

    suspend fun getNewsHeadlines(
        apiKey: String?,
        countryCode: String?,
        category: String?
    ): ArticleResponse?

    suspend fun getCountryInfoByName(
        countryname: String?
    ): List<CountryResponse?>

    suspend fun getTrackingDataByDateRange(
        dateFrom: String?,
        dateTo: String?
    ): TrackingResponse?
}