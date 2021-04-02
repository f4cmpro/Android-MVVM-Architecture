package com.example.testformobiledeveloper.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testformobiledeveloper.data.local.dao.AssignmentDao
import com.example.testformobiledeveloper.data.local.dao.WorkoutDao
import com.example.testformobiledeveloper.data.local.entity.AssignmentEntity
import com.example.testformobiledeveloper.data.local.entity.WorkoutEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [WorkoutEntity::class, AssignmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WorkoutDatabase  : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
    abstract fun assignmentDao(): AssignmentDao
}
