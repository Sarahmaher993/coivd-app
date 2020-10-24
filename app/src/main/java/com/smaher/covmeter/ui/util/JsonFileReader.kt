package com.smaher.covmeter.ui.util

import android.content.Context
import com.smaher.covmeter.R
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException
import java.util.ArrayList

fun loadCountriesJSONFromAsset(context: Context): String? {
        return try {
            val `is` = context.resources.openRawResource(R.raw.convertcsv)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
 }

fun getCountriesFromJson(context: Context): ArrayList<CountryLocation> {
    val userJsonString = loadCountriesJSONFromAsset(context)
    val countries = ArrayList<CountryLocation>()
    try {
        val jsonArray = JSONArray(userJsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            countries.add(
                CountryLocation(
                    name = jsonObject.getString("name"),
                    lat = jsonObject.getDouble("latitude"),
                    lng = jsonObject.getDouble("longitude")
                )
            )
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return countries
}

data class CountryLocation(val name: String?, val lat: Double, val lng: Double)
