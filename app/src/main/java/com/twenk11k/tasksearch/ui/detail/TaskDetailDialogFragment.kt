package com.twenk11k.tasksearch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.databinding.DialogTaskDetailBinding

class TaskDetailDialogFragment : DialogFragment() {

    private var taskItem: TaskItem? = null

    companion object {
        fun newInstance(taskItem: TaskItem): TaskDetailDialogFragment {
            val frag = TaskDetailDialogFragment()
            val args = Bundle()
            args.putParcelable("taskItem", taskItem)
            frag.arguments = args
            return frag
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskItem = arguments?.getParcelable("taskItem")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<DialogTaskDetailBinding>(
            inflater,
            R.layout.dialog_task_detail,
            container,
            false
        ).apply {
            task = taskItem
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

}