package com.twenk11k.tasksearch.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.twenk11k.tasksearch.ui.main.MainViewModel
import com.twenk11k.tasksearch.util.Constants.SEARCH_DELAY

object ViewBinding {

    @JvmStatic
    @BindingAdapter("gone")
    fun bindGone(view: View, isGone: Boolean) {
        view.visibility = if (isGone) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, text: String?) {
        text?.let {
            Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
        }
    }

    @JvmStatic
    @BindingAdapter("tabSelected")
    fun bindTabSelected(tabLayout: TabLayout, viewModel: MainViewModel) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tabLayout.postDelayed({
                    when (tab?.position) {
                        0 -> viewModel.setStatus(0)
                        1 -> viewModel.setStatus(1)
                        2 -> viewModel.setStatus(18)
                    }
                }, SEARCH_DELAY)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}