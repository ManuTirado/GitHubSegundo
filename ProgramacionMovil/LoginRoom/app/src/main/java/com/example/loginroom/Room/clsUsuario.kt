package com.example.loginroom.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_entity")
data class clsUsuario (
    @PrimaryKey(autoGenerate = true) var id:Int = 0,
    var usuario:String,
    var contrasena:String,
    var nombre:String,
    var apellidos:String,
    var foto:String,
        )

