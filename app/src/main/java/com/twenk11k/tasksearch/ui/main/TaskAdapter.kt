package com.twenk11k.tasksearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.databinding.ItemTaskBinding
import com.twenk11k.tasksearch.extension.autoNotify
import kotlin.properties.Delegates

class TaskAdapter(private val clickListener: TaskItemClickListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var items: List<TaskItem> by Delegates.observable(emptyList()) { _, oldList, newList ->
        autoNotify(oldList, newList) { oldItem, newItem -> oldItem.id == newItem.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTaskBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_task,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                clickListener.showTaskDetails(items[absoluteAdapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            task = items[position]
        }
    }

    fun updateAdapter(list: List<TaskItem>) {
        items = list
    }

    inner class ViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

}