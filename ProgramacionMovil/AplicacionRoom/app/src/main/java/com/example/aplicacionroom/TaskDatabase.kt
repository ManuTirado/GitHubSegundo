package com.example.approom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}