package com.smaher.covmeter.ui.news

import androidx.lifecycle.*
import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.repository.CovidUpdatesProvider
import com.smaher.covmeter.domain.usecase.GetNewsByCountryUseCase
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val newsLiveData = MutableLiveData<ArticleResponse?>()
    val errorLiveData = MutableLiveData<String?>()
    val progressLiveData = MutableLiveData<Boolean>()


    fun getNewsForCountry(countryName:String?) {
        viewModelScope.launch {
            progressLiveData.value = true
            try {
                val response = GetNewsByCountryUseCase(CovidUpdatesProvider()).execute(countryName)
                newsLiveData.value = response

            } catch (ex: Exception) {
                errorLiveData.value = ex.localizedMessage
            }
            finally {
                progressLiveData.value = false
            }
        }
    }
}