package com.smaher.covmeter.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.smaher.covmeter.R
import com.smaher.covmeter.data.model.response.ArticleData
import com.smaher.covmeter.data.model.response.ArticleResponse
import com.smaher.covmeter.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment() {

    private lateinit var newsViewModel: NewsViewModel

    private val newsRecyclerAdapter = NewsRecyclerAdapter()
    private val newsObserver by lazy {
        Observer<ArticleResponse?>{
            if (it != null) {
                newsRecyclerAdapter.setData(it.articles as ArrayList<ArticleData>)
            }
        }
    }

    val countryName = "germany"

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
                ViewModelProviders.of(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        observeChanges()
        getNews()
    }

    private fun observeChanges(){
        newsViewModel.newsLiveData.observe(viewLifecycleOwner,newsObserver)
        newsViewModel.errorLiveData.observe(viewLifecycleOwner,failedObserver)
        newsViewModel.progressLiveData.observe(viewLifecycleOwner,progressObserver)
    }

    private fun getNews(){
        newsViewModel.getNewsForCountry(countryName)
    }

    private fun setUpRecycler(){
        RvNews.adapter = newsRecyclerAdapter
    }
}