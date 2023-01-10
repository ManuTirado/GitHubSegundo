package com.example.loginroom.Room

import androidx.room.*

@Dao
interface UsuarioDao {

    // Función que devuelve todas las tareas de la base de datos en una lista Mutable.
    @Query("SELECT * FROM task_entity")
    suspend fun getAllTasks(): MutableList<clsUsuario>

    // Función que añade una tarea, la que se pasa por parámetro, y devuelve el id insertado.
    @Insert
    suspend fun addTask(taskEntity: clsUsuario): Long       // Devuelve Long porque la cantidad de datos guardada puede ser muy alto.

    // Función que busca tareas por id (debe ser Long, no Int)
    @Query("SELECT * FROM task_entity where id like :id")
    suspend fun getTaskById(id: Long): clsUsuario

    // Función que actualiza una tarea y devuelve
    @Update
    suspend fun updateTask(task: clsUsuario): Int

    // Función que borra una tarea y devuelve
    @Delete
    suspend fun deleteTask(task: clsUsuario): Int
}