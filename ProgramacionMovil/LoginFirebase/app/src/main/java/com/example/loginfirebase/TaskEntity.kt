package com.example.loginfirebase

data class TaskEntity (
    var id:Int = 0,
    var nombre:String = "",
    var dificultad:Int,
    var puntuacion:Int,
    var numeroPulsaciones:Int,
    var fechaHora:String
)