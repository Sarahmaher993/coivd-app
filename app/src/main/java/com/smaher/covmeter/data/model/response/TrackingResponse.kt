package com.smaher.covmeter.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TrackingResponse(
    val updated_at: String?,
    val total: TotalSummary?,
    val dates: Map<String?, DateSummary?>
)

data class DateSummary(
    val countries: Map<String?, CountryTrackingDetails?>,
    val info: DateInfo?
)

@Parcelize
data class CountryTrackingDetails(
    val date: String?,
    val id: String?,
    val links: List<Link?>,
    val name: String?,
    val name_es: String,
    val name_it: String,
    val regions: List<Region>,
    val source: String,
    val today_confirmed: Long?,
    val today_deaths: Long?,
    val today_new_confirmed: Long?,
    val today_new_deaths: Long?,
    val today_new_open_cases: Long?,
    val today_new_recovered: Long?,
    val today_open_cases: Long?,
    val today_recovered: Long?,
    val today_vs_yesterday_confirmed: Double?,
    val today_vs_yesterday_deaths: Double?,
    val today_vs_yesterday_open_cases: Double?,
    val today_vs_yesterday_recovered: Double?,
    val yesterday_confirmed: Long?,
    val yesterday_deaths: Long?,
    val yesterday_open_cases: Long?,
    val yesterday_recovered: Long?
):Parcelable

@Parcelize
data class Link(
    val href: String?,
    val rel: String?,
    val type: String?
):Parcelable

@Parcelize
data class Region(
    val date: String,
    val id: String,
    val links: List<Link?>,
    val name: String?,
    val name_es: String?,
    val name_it: String?,
    val source: String?,
    val sub_regions: List<SubRegion?>,
    val today_confirmed: Long?,
    val today_deaths: Long?,
    val today_new_confirmed: Long?,
    val today_new_deaths: Long?,
    val today_new_open_cases: Long?,
    val today_new_recovered: Long?,
    val today_open_cases: Long?,
    val today_recovered: Long?,
    val today_vs_yesterday_confirmed: Double?,
    val today_vs_yesterday_deaths: Double?,
    val today_vs_yesterday_open_cases: Double?,
    val today_vs_yesterday_recovered: Double?,
    val yesterday_confirmed: Long?,
    val yesterday_deaths: Long?,
    val yesterday_open_cases: Long?,
    val yesterday_recovered: Long?
):Parcelable
@Parcelize
data class SubRegion(
    val date: String,
    val id: String,
    val name: String,
    val name_es: String,
    val name_it: String,
    val source: String,
    val today_confirmed: Long?,
    val today_deaths: Long?,
    val today_new_confirmed: Long?,
    val today_new_deaths: Long?,
    val today_new_recovered: Long?,
    val today_recovered: Long?,
    val today_vs_yesterday_confirmed: Double?,
    val today_vs_yesterday_deaths: Double?,
    val today_vs_yesterday_recovered: Double?,
    val yesterday_confirmed: Long?,
    val yesterday_deaths: Long?,
    val yesterday_recovered: Long?
):Parcelable

data class DateInfo(
    val date: String?,
    val date_generation: String?,
    val yesterday: String?
)

data class TotalSummary(
    val date: String?,
    val name: String?,
    val name_es: String?,
    val name_it: String?,
    val rid: String?,
    val source: String?,
    val today_confirmed: Long?,
    val today_deaths: Long?,
    val today_new_confirmed: Long?,
    val today_new_deaths: Long?,
    val today_new_open_cases: Long?,
    val today_new_recovered: Long?,
    val today_open_cases: Long?,
    val today_recovered: Long?,
    val today_vs_yesterday_confirmed: Double?,
    val today_vs_yesterday_deaths: Double?,
    val today_vs_yesterday_open_cases: Double?,
    val today_vs_yesterday_recovered: Double?,
    val yesterday_confirmed: Long?,
    val yesterday_deaths: Long?,
    val yesterday_open_cases: Long?,
    val yesterday_recovered: Long?
)
