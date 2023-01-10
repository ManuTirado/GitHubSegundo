package com.example.simonssays.Room

import android.app.Application
import androidx.room.Room

class PuntuacionesApp: Application() {
    companion object{
        lateinit var database: TasksDatabase
    }

    override fun onCreate() {
        super.onCreate()
        PuntuacionesApp.database = Room.databaseBuilder(this, TasksDatabase::class.java, "task-db").build()
    }
}