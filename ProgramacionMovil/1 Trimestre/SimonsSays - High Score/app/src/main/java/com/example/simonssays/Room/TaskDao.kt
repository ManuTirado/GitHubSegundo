package com.example.simonssays.Room

import androidx.room.*

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