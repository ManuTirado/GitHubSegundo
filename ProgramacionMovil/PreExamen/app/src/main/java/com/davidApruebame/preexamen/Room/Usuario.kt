package com.davidApruebame.preexamen.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_Usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    @ColumnInfo(name = "usuario")
    var usuario: String,
    @ColumnInfo(name = "contraseña")
    var contraseña: String,

    @ColumnInfo(name = "nombre")
    var nombre: String,
    @ColumnInfo(name = "apellidos")
    var apellidos: String,
    @ColumnInfo(name = "rutaImagen")
    var rutaImagen: String
)