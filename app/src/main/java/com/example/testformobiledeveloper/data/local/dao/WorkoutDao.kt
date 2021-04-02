package com.example.testformobiledeveloper.data.local.dao

import androidx.paging.DataSource
import androidx.room.*
import com.example.testformobiledeveloper.data.local.entity.WorkoutEntity
import com.example.testformobiledeveloper.data.local.entity.WorkoutWithAssignments
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(entity: List<WorkoutEntity>)

    @Query("SELECT * FROM tb_work_out")
    fun getAll() : List<WorkoutEntity>

    @Transaction
    @Query("SELECT * FROM tb_work_out")
    fun getWorkoutWithAssignments(): DataSource.Factory<Int, WorkoutWithAssignments>

    @Query("DELETE FROM tb_work_out")
    suspend fun deleteAll()
}