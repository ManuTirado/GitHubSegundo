package com.davidApruebame.preexamen.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Usuario::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun usuarios_Dao(): Usuarios_Dao
}