package com.twenk11k.tasksearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.databinding.ItemTaskBinding

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val items = ArrayList<TaskItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            task = items[position]
        }
    }

    fun updateAdapter(list: List<TaskItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

}