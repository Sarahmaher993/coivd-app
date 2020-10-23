package com.smaher.covmeter.domain.repository

import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse


internal interface CovidUpdateRemoteRepository {

    suspend fun getNewsHeadlines(apiKey:String?, countryCode:String?, category:String?): ArticleResponse?

    suspend fun getCountryInfoByName(countryName: String?):List<CountryResponse?>

    suspend fun getTrackingDataByDateRange(dateFrom:String?,dateTo:String?):TrackingResponse?

}