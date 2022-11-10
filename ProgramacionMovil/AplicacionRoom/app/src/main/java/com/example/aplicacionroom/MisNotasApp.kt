package com.example.approom

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class MisNotasApp:Application() {
    companion object{
        lateinit var database: TaskDatabase
    }

    override fun onCreate() {
        super.onCreate()
        MisNotasApp.database = Room.databaseBuilder(this, TaskDatabase::class.java, "task-db").build()
    }
}