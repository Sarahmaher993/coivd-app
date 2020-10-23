package com.smaher.covmeter.data.repository

import com.smaher.covmeter.data.di.CovidServicesModule
import com.smaher.covmeter.domain.repository.CovidUpdatesRepository

class CovidUpdatesProvider  {

    private val covidUpdatesRepository: CovidUpdatesRepositoryImpl =
        CovidUpdatesRepositoryImpl(
            CovidServicesModule().getCovidUpdatesRemoteRepository()
        )

    fun getServices(): CovidUpdatesRepository {
        return covidUpdatesRepository
    }

    class Builder {

        fun build(): CovidUpdatesProvider {
            return CovidUpdatesProvider()
        }
    }
}