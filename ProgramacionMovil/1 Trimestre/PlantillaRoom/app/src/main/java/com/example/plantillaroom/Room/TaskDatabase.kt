package com.example.plantillaroom.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.plantillaroom.Room.TaskDao
import com.example.plantillaroom.Room.TaskEntity

@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}