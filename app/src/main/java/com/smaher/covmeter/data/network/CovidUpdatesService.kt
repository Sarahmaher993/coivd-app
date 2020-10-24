package com.smaher.covmeter.data.network

import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

internal interface TrackingRemoteService {
    @GET("/api?")
    suspend fun getTrackingDataPerDayRange(
        @Query("date_from") dateFrom: String?,
        @Query("date_to") dateTo: String?
    ): TrackingResponse?
}

internal interface NewsRemoteService {

    @GET("/v2/top-headlines")
    suspend fun getCountryHeadlines(
        @Query("country") countryCode: String?,
        @Query("apiKey") apiKey: String?,
        @Query("category") category: String?
    ): ArticleResponse

}

internal interface CountriesRemoteService {

    @GET("/rest/v2/name/{name}")
    suspend fun getCountryByName(@Path("name") countryName: String?): List<CountryResponse?>
}