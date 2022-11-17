package com.example.plantillaroom.Room

import androidx.room.*
import com.example.plantillaroom.Room.TaskEntity

/**
 * TaskDao proporciona los métodos que el resto de la app usa para interactuar con los datos de la tabla.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    suspend fun getAllTasks(): MutableList<TaskEntity>

    @Query("SELECT * FROM task_entity WHERE id = :id")
    suspend fun getTaskById(id:Int): TaskEntity

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(taskEntity : TaskEntity):Long

    @Update (onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}