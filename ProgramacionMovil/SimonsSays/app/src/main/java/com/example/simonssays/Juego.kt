package com.example.simonssays

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Juego : AppCompatActivity(), Comunicador {

    private val verde = 1
    private val rojo = 2
    private val amarillo = 3
    private val azul = 4

    private var patron: MutableList<Int> = mutableListOf(1)

    private var btnVerde:Button = findViewById(R.id.btnVerde)
    private var btnRojo:Button = findViewById(R.id.btnRojo)
    private var btnAmarillo:Button = findViewById(R.id.btnAmarillo)
    private var btnAzul:Button = findViewById(R.id.btnAzul)

    private var puntuacion:Int = 0
    private var jugando:Boolean = false
    private var perdido:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        puntuacion = 0
        logicaPrincipalJuego()
    }

    private fun logicaPrincipalJuego() {
        while (!perdido) {
            mirar()
            jugar()
        }
    }

    private fun jugar() {
        jugando = true

        jugando = false
    }


    private fun mirar() {
        patron.add((1..4).random())
        for (item in patron) {
            when (item) {
                1 -> {
                    btnVerde.background.setTint(resources.getColor(R.color.greenSelected))
                }
                2 -> {
                    btnRojo.background.setTint(resources.getColor(R.color.redSelected))
                }
                3 -> {
                    btnAmarillo.background.setTint(resources.getColor(R.color.yellowSelected))
                }
                4 -> {
                    btnAzul.background.setTint(resources.getColor(R.color.blueSelected))

                }
            }

        }
    }

    override fun onClickVerde() {
        if (jugando) {

        }
    }

    override fun onClickRojo() {
        if (jugando) {

        }
    }

    override fun onClickAmarillo() {
        if (jugando) {

        }
    }

    override fun onClickAzul() {
        if (jugando) {

        }
    }


}