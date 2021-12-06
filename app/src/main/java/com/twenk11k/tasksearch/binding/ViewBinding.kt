package com.twenk11k.tasksearch.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.tabs.TabLayout
import com.twenk11k.tasksearch.ui.main.MainViewModel

object ViewBinding {

    @JvmStatic
    @BindingAdapter("tabSelected")
    fun bindTabSelected(tabLayout: TabLayout, viewModel: MainViewModel) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}