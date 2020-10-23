package com.smaher.covmeter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.smaher.covmeter.R
import com.smaher.covmeter.data.model.response.ArticleData
import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.data.model.response.TrackingResponse
import com.smaher.covmeter.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    private lateinit var homeViewModel: HomeViewModel

    private val trackingDataObserver by lazy {
        Observer<TrackingResponse?>{
            if (it != null) {
                setTrackingTotalSummary(it)
            }
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
        }
        observeChanges()
        homeViewModel.getTrackingData()
    }

    private fun observeChanges(){
        homeViewModel.trackingLiveData.observe(viewLifecycleOwner,trackingDataObserver)
        homeViewModel.errorLiveData.observe(viewLifecycleOwner,failedObserver)
        homeViewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
    }

    private fun setTrackingTotalSummary(trackingResponse: TrackingResponse){
        tvConfirmedCases.text = trackingResponse.total?.today_new_confirmed.toString()
        tvRecoveredCases.text = trackingResponse.total?.today_new_recovered.toString()
        tvDeathCases.text = trackingResponse.total?.today_new_deaths.toString()
    }
}