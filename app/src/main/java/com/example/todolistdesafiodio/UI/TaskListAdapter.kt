package com.example.todolistdesafiodio.UI

import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistdesafiodio.Model.Task
import com.example.todolistdesafiodio.databinding.ItemTaskBinding

class TaskListAdapter : ListAdapter<Task.
TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit : (Task) -> unit = {}
    var listenerDelete : (Task) -> unit = {}

    override fun onCreateViewHolder(parent: ViewGroup. viewType: int): TaskViewHolder(
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTaskBinding.inflate(inflater. parent. false)
        return TaskViewHolder(binding)
    )

    override fun onBindViewHolder(hoder: TaskViewHolder. position: Int) {
        holder.bind(getItem(position))

    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
        ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(item: Task) {
                binding.tvTitle.text = item.title
                binding.tvDate.text = "${item.date} ${item.hour}"
                binding.ivMore.setOnClickListener {
                    showPopup(item)
                }
            }

        private fun showPopup() {
            val ivMore = binding.ivMore
            val popupMenu = PopupMenu(ivMore.context, ivMore)
            popupMenu.menuInflater.Inflate(R.Menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.ItemId) {
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }

    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}
