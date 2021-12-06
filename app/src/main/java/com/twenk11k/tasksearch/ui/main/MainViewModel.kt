package com.twenk11k.tasksearch.ui.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)
    var taskListLiveData: LiveData<List<TaskItem>?>
    val isLoading = ObservableBoolean(false)
    val toastMessage = ObservableField<String>()

    private var query = ""
    private var status = 0

    init {
        taskListLiveData = loadTrigger.switchMap {
            mainRepository.getTaskSearch(
                query = query,
                status = status,
                onStart = { isLoading.set(true) },
                onComplete = { isLoading.set(false) },
                onError = { toastMessage.set(it) }
            ).asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
        }
    }

    fun setQuery(query: String) {
        this.query = query
        loadTrigger.value = Unit
    }

    fun setStatus(status: Int) {
        if (this.status == status) {
            return
        }
        this.status = status
        setQuery(query)
    }

}