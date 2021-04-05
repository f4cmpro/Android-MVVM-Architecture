package com.example.testformobiledeveloper.data.repository

import com.example.testformobiledeveloper.data.local.dao.AssignmentDao
import com.example.testformobiledeveloper.data.local.dao.WorkoutDao
import com.example.testformobiledeveloper.data.local.entity.AssignmentEntity
import com.example.testformobiledeveloper.data.local.entity.WorkoutEntity
import com.example.testformobiledeveloper.data.model.WorkoutData
import com.example.testformobiledeveloper.data.remote.GetWorkoutListApiService
import com.example.testformobiledeveloper.data.retrofit.ErrorResponse
import com.example.testformobiledeveloper.data.retrofit.IRepository
import com.example.testformobiledeveloper.data.retrofit.Response
import com.example.testformobiledeveloper.data.retrofit.TaskResult
import com.example.testformobiledeveloper.ui.WorkoutViewModel
import com.example.testformobiledeveloper.util.DateUtils
import javax.inject.Inject
import javax.inject.Singleton

interface IWorkoutListRepository {
    suspend fun getWorkoutList(
        page: Int,
        isRefresh: Boolean
    ): TaskResult<List<WorkoutData>, ErrorResponse>

    suspend fun clearCache()
}

@Singleton
class WorkoutListRepository @Inject constructor(
    private val getWorkoutListApiService: GetWorkoutListApiService,
    private val workoutDao: WorkoutDao,
    private val assignmentDao: AssignmentDao
) : IRepository, IWorkoutListRepository {

    override suspend fun getWorkoutList(
        page: Int,
        isRefresh: Boolean
    ): TaskResult<List<WorkoutData>, ErrorResponse> {
        return when (val res = execute(getWorkoutListApiService.getWorkoutListAsync())) {
            is Response.Success -> {
                val listWorkoutEntity = ArrayList<WorkoutEntity>()
                workoutDao.addAll(
                    res.value.workouts.flatMap { workout ->
                        val listAssignments = ArrayList<AssignmentEntity>()
                        assignmentDao.addAll(
                            workout.assignments.flatMap { assignment ->
                                if (workout.day + 2 < DateUtils.getCurrentDayOfWeek() && assignment.status != WorkoutViewModel.AssignmentStatus.MISSED.id) {
                                    assignment.status = WorkoutViewModel.AssignmentStatus.MISSED.id
                                } else if (workout.day + 2 >= DateUtils.getCurrentDayOfWeek() && assignment.status == WorkoutViewModel.AssignmentStatus.MISSED.id) {
                                    assignment.status =
                                        WorkoutViewModel.AssignmentStatus.ASSIGNED.id
                                }
                                listAssignments.add(
                                    AssignmentEntity(
                                        assignment.id, assignment.title,
                                        assignment.status, assignment.totalExercise, workout.id
                                    )
                                )
                                listAssignments
                            }
                        )
                        val workoutEntity = WorkoutEntity(workout.id, workout.day, page)
                        listWorkoutEntity.add(workoutEntity)
                        listWorkoutEntity
                    }
                )
                TaskResult.Success(res.value.workouts)
            }
            is Response.Failure -> {
                TaskResult.Failure(res.value)
            }
        }
    }

    override suspend fun clearCache() {
        workoutDao.deleteAll()
        assignmentDao.deleteAll()
    }
}