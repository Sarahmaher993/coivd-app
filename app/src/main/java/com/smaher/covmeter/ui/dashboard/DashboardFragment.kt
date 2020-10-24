package com.smaher.covmeter.ui.dashboard


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.smaher.covmeter.R
import com.smaher.covmeter.data.model.response.CountryTrackingDetails
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val countryTrackingDetails by lazy {
        arguments?.getParcelable(TRACKING_DETAILS) as CountryTrackingDetails?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        dashboardViewModel.getCountryInfo(countryTrackingDetails?.name)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTrackingData()
        initListeners()
    }

    private fun initTrackingData() {
        tvCountryName.text = countryTrackingDetails?.name
        tvNewConfirmedCases.text = countryTrackingDetails?.today_new_confirmed.toString()
        tvNewDeathCases.text = countryTrackingDetails?.today_new_deaths.toString()
        tvNewRecoveredCases.text = countryTrackingDetails?.today_new_recovered.toString()
        tvYesterdayConfirmed.text = countryTrackingDetails?.yesterday_confirmed.toString()
        tvYesterdayDeaths.text = countryTrackingDetails?.yesterday_deaths.toString()
        tvYesterdayRecovered.text = countryTrackingDetails?.yesterday_recovered.toString()
    }

    private fun initListeners() {
        btnNews.setOnClickListener {
            findNavController().navigate(
                DashboardFragmentDirections.actionNavigationDashboardToNavigationNotifications(
                    countryTrackingDetails?.name
                )
            )
        }
    }

    companion object {
        const val TRACKING_DETAILS = "countryTrackingDetails"
    }

}