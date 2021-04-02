package com.example.testformobiledeveloper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testformobiledeveloper.R
import com.example.testformobiledeveloper.data.model.AssignmentData
import com.example.testformobiledeveloper.databinding.ItemAssignmentBinding

class AssignmentAdapter(
    private val viewModel : WorkoutViewModel
) : ListAdapter<AssignmentData, AssignmentAdapter.AssignmentViewHolder>(DIFF_CALLBACK){
    inner class AssignmentViewHolder(private val parent : ViewGroup,
                                     private val binding : ItemAssignmentBinding =
                                  DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                                      R.layout.item_assignment, parent, false))
        : RecyclerView.ViewHolder(binding.root){
            fun bind(assignmentData : AssignmentData, workoutViewModel: WorkoutViewModel) {
                binding.apply {
                    data = assignmentData
                    viewModel = workoutViewModel
                    binding.notifyChange()
                    binding.executePendingBindings()
                }
                itemView.setOnClickListener {
                    assignmentData.isChecked = !assignmentData.isChecked
                    binding.data = assignmentData
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssignmentViewHolder {
        return AssignmentViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AssignmentViewHolder, position: Int) {
        val itemData = getItem(position)
        holder.bind(itemData, viewModel)
    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<AssignmentData> = object : DiffUtil.ItemCallback<AssignmentData>() {
            override fun areItemsTheSame(oldItem: AssignmentData, newItem: AssignmentData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AssignmentData, newItem: AssignmentData): Boolean {
                return oldItem == newItem
            }
        }

    }
}