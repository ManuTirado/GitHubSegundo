package com.example.loginroom.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(clsUsuario::class), version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): UsuarioDao
}