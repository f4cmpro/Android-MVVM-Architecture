package com.example.testformobiledeveloper.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WorkoutWithAssignments(
    @Embedded val workout : WorkoutEntity,
    @Relation(parentColumn = "id", entityColumn = "workout_id") val assignments : List<AssignmentEntity>
)