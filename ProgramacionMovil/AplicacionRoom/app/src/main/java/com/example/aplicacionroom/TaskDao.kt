package com.example.approom

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    suspend fun getAllTasks(): MutableList<TaskEntity>
    @Query("SELECT * FROM task_entity WHERE id = :id")
    suspend fun getTaskById(id:Long):TaskEntity
    @Insert
    suspend fun addTask(taskEntity : TaskEntity):Long
    @Update
    suspend fun updateTask(task: TaskEntity)
    @Delete
    suspend fun deleteTask(task: TaskEntity)
}