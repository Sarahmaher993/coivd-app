package com.smaher.covmeter.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.smaher.covmeter.R
import com.smaher.covmeter.data.model.response.CountryTrackingDetails
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.ui.base.BaseFragment
import com.smaher.covmeter.ui.util.getCountriesFromJson
import kotlinx.android.synthetic.main.view_marker_info_window.view.*


class HomeFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var homeViewModel: HomeViewModel

    var map: GoogleMap? = null

    val markersMap: HashMap<Marker?, CountryTrackingDetails?> = HashMap()

    private val trackingDataObserver by lazy {
        Observer<TrackingResponse?> {
            if (it != null) {
                //setTrackingTotalSummary(it)
                addCountriesMarkers(it)
                initMap()
            }
        }
    }

    val customPin by lazy{
        val img = BitmapFactory.decodeResource(resources, R.drawable.ic_location_pin)
        BitmapDescriptorFactory.fromBitmap(img)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.getTrackingData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeChanges()
        val map =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        map.getMapAsync(this)

    }

    private fun observeChanges() {
        homeViewModel.trackingLiveData.observe(viewLifecycleOwner, trackingDataObserver)
        homeViewModel.errorLiveData.observe(viewLifecycleOwner, failedObserver)
        homeViewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
    }

   /* private fun setTrackingTotalSummary(trackingResponse: TrackingResponse) {
        tvConfirmedCases.text = trackingResponse.total?.today_new_confirmed.toString()
        tvRecoveredCases.text = trackingResponse.total?.today_new_recovered.toString()
        tvDeathCases.text = trackingResponse.total?.today_new_deaths.toString()
    }*/

    private fun getCountryCentralPinLocation(countryTrackingDetails: CountryTrackingDetails?) {
        val countryLocation = countriesLocations?.firstOrNull() {
            it.name?.toLowerCase() == countryTrackingDetails?.name?.toLowerCase()
        }
        countryLocation?.let {
            markersMap.put(map?.addMarker(it.lat, it.lng), countryTrackingDetails)
        }
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            this.map = p0
            map?.uiSettings?.isZoomControlsEnabled = true
            map!!.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(STARTING_LAT, STARTING_LNG),
                    ZOOM_LEVEL
                )
            )
        }

    }

    private fun addCountriesMarkers(trackingResponse: TrackingResponse?) {
        trackingResponse?.dates?.forEach {
            it.value?.countries?.forEach { s, countryTrackingDetails ->
                countryTrackingDetails?.let {
                    getCountryCentralPinLocation(countryTrackingDetails)
                }
            }
        }
    }

    private fun GoogleMap.addMarker(lat: Double, lang: Double): Marker? {

        return addMarker(
            MarkerOptions().position(
                LatLng(
                    lat,
                    lang
                )
            ).icon(customPin)
        )
    }

    private fun initMap() {

        map?.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            lateinit var view: View
            override fun getInfoContents(p0: Marker?): View {
                return view
            }

            override fun getInfoWindow(marker: Marker): View? {
                view = layoutInflater.inflate(R.layout.view_marker_info_window, null)
                view.country.text = markersMap[marker]?.name
                view.tvConfirmedCases.text = String.format(
                    getString(R.string.new_confirmed_cases_map),
                    markersMap[marker]?.today_new_confirmed.toString()
                )
                return view
            }
        })

        map?.setOnInfoWindowClickListener {
            val countryTrackingDetails = markersMap[it]
            context?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationDashboard(
                        countryTrackingDetails
                    )
                )
            }
        }

    }

    private val countriesLocations by lazy {
        context?.let { getCountriesFromJson(it) }
    }

    companion object {
        const val STARTING_LAT = 31.963158
        const val STARTING_LNG = 35.930359
        const val ZOOM_LEVEL = 5f
    }
}