package com.smaher.covmeter.domain.usecase

import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider

class GetCountryUseCase(private val covidUpdatesProvider: CovidUpdatesProvider) {
    suspend fun execute(countryName: String?): List<CountryResponse?> {
        return covidUpdatesProvider.getServices().getCountryInfoByName(countryName)
    }
}