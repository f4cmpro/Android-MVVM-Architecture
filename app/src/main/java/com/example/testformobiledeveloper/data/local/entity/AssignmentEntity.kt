package com.example.testformobiledeveloper.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_assignment")
data class AssignmentEntity (
    @PrimaryKey @ColumnInfo(name = "id") val id : String = "",
    @ColumnInfo(name = "title") val title : String = "",
    @ColumnInfo(name = "status") val status : Int = 0,
    @ColumnInfo(name = "total_exercises") val totalExercises : Int = 0,
    @ColumnInfo(name = "workout_id") val workoutId : String = ""
)