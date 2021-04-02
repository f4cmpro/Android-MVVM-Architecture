package com.example.testformobiledeveloper.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tb_work_out")
data class WorkoutEntity(
        @PrimaryKey @ColumnInfo(name = "id") val workoutId: String = "",
        @ColumnInfo(name = "day") val day: Int = 0,
        @ColumnInfo(name = "page") val page: Int = 1,
)