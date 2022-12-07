package com.example.loginroom.Room

import android.app.Application
import androidx.room.Room

class LoginApp:Application() {
    companion object {
        lateinit var database: TasksDatabase
    }
    override fun onCreate() {
        super.onCreate()
        LoginApp.database =  Room.databaseBuilder(this, TasksDatabase::class.java, "tasks-db").build()
    }
}