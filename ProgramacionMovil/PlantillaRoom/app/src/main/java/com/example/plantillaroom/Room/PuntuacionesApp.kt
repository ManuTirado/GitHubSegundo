package com.example.plantillaroom.Room

import android.app.Application
import androidx.room.Room

/**
 * Clase que crea la BD
 */
class PuntuacionesApp: Application() {
    companion object{
        lateinit var database: TasksDatabase    // BB "estática" para poder acceder desde el resto de lugares
    }

    override fun onCreate() {
        super.onCreate()
        PuntuacionesApp.database = Room.databaseBuilder(this, TasksDatabase::class.java, "task-db").build()
    }
}