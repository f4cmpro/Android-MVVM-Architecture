package com.example.testformobiledeveloper.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testformobiledeveloper.data.local.entity.AssignmentEntity

@Dao
interface AssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(entity: List<AssignmentEntity>)

    @Query("SELECT * FROM tb_assignment")
    fun getAll() : List<AssignmentEntity>

    @Query("DELETE FROM tb_assignment")
    suspend fun deleteAll()
}