package com.example.loginfirebase

data class TaskEntity (
    var email:String = "",
    var nombre:String = "",
    var dificultad:Int,
    var puntuacion:Int,
    var numeroPulsaciones:Int,
    var fechaHora:String
)