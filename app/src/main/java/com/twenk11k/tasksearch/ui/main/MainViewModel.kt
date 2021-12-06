package com.twenk11k.tasksearch.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.twenk11k.tasksearch.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    var taskListLiveData: LiveData<List<Any>?>
    val isLoading = ObservableBoolean(false)
    val toastMessage = ObservableField<String>()

    private val query = MutableStateFlow("")

    init {
        taskListLiveData = query.asLiveData().switchMap {
            mainRepository.getTaskSearch(
                query = query.value,
                onStart = { isLoading.set(true) },
                onComplete = { isLoading.set(false) },
                onError = { toastMessage.set(it) }
            ).asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
        }
    }

    fun setQuery(query: String) {
        this.query.value = query
    }

}