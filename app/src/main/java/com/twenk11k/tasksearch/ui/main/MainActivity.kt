package com.twenk11k.tasksearch.ui.main

import android.os.Bundle
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.binding.DataBindingActivity
import com.twenk11k.tasksearch.databinding.ActivityMainBinding

class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
        }
    }

}