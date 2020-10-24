package com.smaher.covmeter.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider
import com.smaher.covmeter.domain.usecase.GetTrackingDataUseCase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {


    val trackingLiveData = MutableLiveData<TrackingResponse?>()
    val errorLiveData = MutableLiveData<String?>()
    val progressLiveData = MutableLiveData<Boolean>()


    fun getTrackingData() {
        viewModelScope.launch {
            progressLiveData.value = true
            try {
                val response = GetTrackingDataUseCase(CovidUpdatesProvider()).execute(getYesterday(),getCurrentDate())
                trackingLiveData.value = response

            } catch (ex: Exception) {
                errorLiveData.value = ex.localizedMessage
            }
            finally {
                progressLiveData.value = false
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate():String?{
        val date = Calendar.getInstance().time
        val formatter =  SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getYesterday():String?{
        val date = Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)
        val formatter =  SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }



}