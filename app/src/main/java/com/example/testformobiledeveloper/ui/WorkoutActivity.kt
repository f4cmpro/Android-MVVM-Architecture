package com.example.testformobiledeveloper.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testformobiledeveloper.R
import com.example.testformobiledeveloper.databinding.WorkoutActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class WorkoutActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: WorkoutActivityBinding
    private lateinit var workoutViewModel: WorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.workout_activity)
        workoutViewModel =
            ViewModelProvider(this, viewModelFactory).get(WorkoutViewModel::class.java)
        val monAdapter = AssignmentAdapter(workoutViewModel)
        val tueAdapter = AssignmentAdapter(workoutViewModel)
        val wedAdapter = AssignmentAdapter(workoutViewModel)
        val thuAdapter = AssignmentAdapter(workoutViewModel)
        val friAdapter = AssignmentAdapter(workoutViewModel)
        val satAdapter = AssignmentAdapter(workoutViewModel)
        val sunAdapter = AssignmentAdapter(workoutViewModel)
        binding.apply {
            viewModel = workoutViewModel

            itemMonday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = monAdapter
            }

            itemTuesday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = tueAdapter
            }

            itemWednesday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = wedAdapter
            }

            itemThursday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = thuAdapter
            }

            itemFriday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = friAdapter
            }

            itemSaturday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = satAdapter
            }

            itemSunday.recycleAssignment.apply {
                layoutManager = LinearLayoutManager(this@WorkoutActivity)
                adapter = sunAdapter
            }

            refresh.apply {
                setOnRefreshListener {
                    workoutViewModel.refresh()
                }
            }
        }
        workoutViewModel.apply {
            assignments0.observe(this@WorkoutActivity, {
                monAdapter.submitList(it)
            })
            assignments1.observe(this@WorkoutActivity, {
                tueAdapter.submitList(it)
            })
            assignments2.observe(this@WorkoutActivity, {
                wedAdapter.submitList(it)
            })
            assignments3.observe(this@WorkoutActivity, {
                thuAdapter.submitList(it)
            })
            assignments4.observe(this@WorkoutActivity, {
                friAdapter.submitList(it)
            })
            assignments5.observe(this@WorkoutActivity, {
                satAdapter.submitList(it)
            })
            assignments6.observe(this@WorkoutActivity, {
                sunAdapter.submitList(it)
            })

            loadingState.observe(this@WorkoutActivity, {
                binding.loadingProgress.visibility =
                    if (it.isLoading()) View.VISIBLE else View.GONE
            })

            refreshState.observe(this@WorkoutActivity, {
                if(it.isRefreshing()){
                    monAdapter.submitList(ArrayList())
                    tueAdapter.submitList(ArrayList())
                    wedAdapter.submitList(ArrayList())
                    thuAdapter.submitList(ArrayList())
                    friAdapter.submitList(ArrayList())
                    satAdapter.submitList(ArrayList())
                    sunAdapter.submitList(ArrayList())
                }
                binding.refresh.isRefreshing = false
            })
        }
        workoutViewModel.firstLoad()
    }
}