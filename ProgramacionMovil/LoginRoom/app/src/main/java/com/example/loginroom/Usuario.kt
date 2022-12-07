package com.example.loginroom

import androidx.room.PrimaryKey
import com.example.loginroom.Room.clsUsuario

class Usuario : java.io.Serializable {
    var id: Int

    var usuario: String

    var contrasena: String

    var nombre: String

    var apellidos: String

    var foto: String

    constructor(clsUsuario: clsUsuario) {
        this.id = clsUsuario.id
        this.usuario = clsUsuario.usuario
        this.contrasena = clsUsuario.contrasena
        this.nombre = clsUsuario.nombre
        this.apellidos = clsUsuario.apellidos
        this.foto = clsUsuario.foto
    }
}