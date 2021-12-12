package com.twenk11k.tasksearch.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.databinding.ActivityMainBinding
import com.twenk11k.tasksearch.ui.binding.DataBindingActivity
import com.twenk11k.tasksearch.ui.detail.TaskDetailDialogFragment
import com.twenk11k.tasksearch.util.Constants.SEARCH_DELAY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            rvTasks.adapter = TaskAdapter(object : TaskItemClickListener {
                override fun showTaskDetails(taskItem: TaskItem) {
                    displayTaskDetailsDialog(taskItem)
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        handleSearch(menu)
        return true
    }

    private fun handleSearch(menu: Menu) {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        postDelayed({
                            viewModel.setQuery(it)
                        }, SEARCH_DELAY)
                    }
                    return true
                }
            })
        }
    }

    private fun displayTaskDetailsDialog(taskItem: TaskItem) {
        val taskDetailDialogFragment = TaskDetailDialogFragment.newInstance(taskItem)
        taskDetailDialogFragment.show(supportFragmentManager, "task_detail_dialog_fragment")
    }

}