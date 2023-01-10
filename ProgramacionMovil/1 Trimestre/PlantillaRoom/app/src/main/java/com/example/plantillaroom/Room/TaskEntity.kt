package com.example.plantillaroom.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

/**
 * Entidad que guardamos en la base de datos
 */
@Entity(tableName = "task_entity")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var nombre:String = "",
    var dificultad:Int,
    var puntuacion:Int,
    var numeroPulsaciones:Int,
    var fechaHora:String
)