package com.smaher.covmeter.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


abstract class BaseFragment : Fragment() {

    private var loadingProgressBar: ProgressDialog? = null

    protected val failedObserver by lazy {
        Observer<String?>{
            showToast(it)
        }
    }

    protected val progressObserver by lazy {
        Observer<Boolean>{
            if(it) showLoading() else{
                this.hideLoading()
            }
        }
    }

    override fun onDestroy() {
        loadingProgressBar?.cancel()
        loadingProgressBar = null
        super.onDestroy()
    }


    protected fun showLoading() {
        if (loadingProgressBar == null) {
            loadingProgressBar = ProgressDialog(context)
        }

        if (loadingProgressBar?.isShowing == false) {
            loadingProgressBar?.show()
        }
    }


    protected fun hideLoading() {
        loadingProgressBar?.hide()
    }


    fun showToast(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}