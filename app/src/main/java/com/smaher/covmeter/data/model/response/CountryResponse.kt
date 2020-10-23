package com.smaher.covmeter.data.model.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val callingCodes: List<String>,
    val capital: String,
    val cioc: String,
    val currencies: List<CountryCurrency>,
    val demonym: String,
    val flag: String,
    val gini: Double,
    val languages: List<Language>,
    val latlng: List<Double>,
    val name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val regionalBlocs: List<RegionalBlocs>,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: CountryNameTranslation
)

data class RegionalBlocs(
    val acronym: String?,
    val name: String?,
    val otherAcronyms: List<Any>?,
    val otherNames: List<String>?

)

data class CountryCurrency(
    val code: String?,
    val name: String?,
    val symbol: String?
)

data class CountryNameTranslation(
    val br: String?,
    val de: String?,
    val es: String?,
    val fr: String?,
    @SerializedName("it") val italian: String?,
    val ja: String?,
    val pt: String?
)

data class Language(
    val iso639_1: String?,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
)

