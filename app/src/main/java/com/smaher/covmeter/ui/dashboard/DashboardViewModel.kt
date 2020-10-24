package com.smaher.covmeter.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaher.covmeter.data.model.response.CountryResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider
import com.smaher.covmeter.domain.usecase.GetCountryUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class DashboardViewModel : ViewModel() {

    val countryInfoLiveData = MutableLiveData<CountryResponse?>()

    fun getCountryInfo(countryName:String?){
        viewModelScope.launch {
            try {
               countryInfoLiveData.value =  GetCountryUseCase(CovidUpdatesProvider()).execute(countryName).firstOrNull()
            }catch (ex: Exception){

            }
        }

    }
}