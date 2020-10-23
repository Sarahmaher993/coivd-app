package com.smaher.covmeter.domain.usecase

import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider

class GetTrackingDataUseCase constructor(private val covidUpdatesProvider: CovidUpdatesProvider) {
    suspend fun execute(dateTo:String?): TrackingResponse? {
        return covidUpdatesProvider.getServices().getTrackingDataByDateRange(dateTo,dateTo)
    }

}