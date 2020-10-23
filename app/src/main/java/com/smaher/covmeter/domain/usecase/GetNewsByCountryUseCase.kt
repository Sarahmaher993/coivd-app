package com.smaher.covmeter.domain.usecase

import com.smaher.covmeter.BuildConfig
import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider

class GetNewsByCountryUseCase constructor(private val covidUpdatesProvider: CovidUpdatesProvider) {
    suspend fun execute(countryName: String?): ArticleResponse? {
        val countryInfo = covidUpdatesProvider.getServices().getCountryInfoByName(countryName)
        val articles = covidUpdatesProvider.getServices().getNewsHeadlines(
            BuildConfig.NEWS_API_KEY, countryInfo.first()?.alpha2Code,
            NEWS_HEALTH_CATEGORY
        )
        return articles
    }

    companion object {
        const val NEWS_HEALTH_CATEGORY = "health"
    }
}