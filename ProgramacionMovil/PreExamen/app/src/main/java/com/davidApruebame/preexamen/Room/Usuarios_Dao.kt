package com.davidApruebame.preexamen.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Usuarios_Dao {

    @Query("SELECT * FROM tabla_Usuarios")
    suspend fun getAllUsers(): MutableList<Usuario>

    @Insert
    suspend fun addUser(usuario: Usuario): Long

    @Query("SELECT * FROM tabla_Usuarios WHERE id like:id")
    suspend fun getUserById(id: Long): Usuario

}