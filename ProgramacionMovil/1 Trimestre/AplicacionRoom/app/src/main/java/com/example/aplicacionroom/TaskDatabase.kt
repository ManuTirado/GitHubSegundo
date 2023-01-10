package com.example.approom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplicacionroom.TaskDao

@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}