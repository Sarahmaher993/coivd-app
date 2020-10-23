package com.smaher.covmeter.ui.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaher.covmeter.BuildConfig
import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider
import com.smaher.covmeter.domain.usecase.GetNewsByCountryUseCase
import com.smaher.covmeter.domain.usecase.GetTrackingDataUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
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
                val response = GetTrackingDataUseCase(CovidUpdatesProvider()).execute(getCurrentDate())
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
        val formatter =  SimpleDateFormat("yyyy-MM-dd") //or use getDateInstance()
        return formatter.format(date)
    }

}