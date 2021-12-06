package com.twenk11k.tasksearch.ui.main

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.binding.DataBindingActivity
import com.twenk11k.tasksearch.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

}