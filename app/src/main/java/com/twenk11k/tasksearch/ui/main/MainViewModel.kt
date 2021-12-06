package com.twenk11k.tasksearch.ui.main

import androidx.lifecycle.ViewModel
import com.twenk11k.tasksearch.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel()