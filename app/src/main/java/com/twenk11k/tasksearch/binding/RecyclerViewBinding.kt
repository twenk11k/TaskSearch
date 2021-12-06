package com.twenk11k.tasksearch.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.tasksearch.ui.main.TaskAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterTaskList")
    fun bindAdapterTaskList(
        view: RecyclerView,
        list: List<Any>?
    ) {
        list?.let {
            (view.adapter as TaskAdapter).updateAdapter(it)
        }
    }

}