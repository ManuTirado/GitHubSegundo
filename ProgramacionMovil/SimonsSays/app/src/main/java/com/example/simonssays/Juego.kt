package com.example.simonssays

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Juego : AppCompatActivity() {

    private val verde = 1
    private val rojo = 2
    private val amarillo = 3
    private val azul = 4
    private val contexto: Context = this

    private var patron: MutableList<Int> = mutableListOf(1, 2, 3)
    private var contPulsaciones: Int = 0

    private var puntuacion: Int = 0
    private var jugando: Boolean = false
    private var perdido: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        val btnVerde: Button = findViewById(R.id.btnVerde)
        val btnRojo: Button = findViewById(R.id.btnRojo)
        val btnAmarillo: Button = findViewById(R.id.btnAmarillo)
        val btnAzul: Button = findViewById(R.id.btnAzul)
        btnVerde.setOnClickListener {
            onClickVerde()
        }
        btnRojo.setOnClickListener {
            onClickRojo()
        }
        btnAmarillo.setOnClickListener {
            onClickAmarillo()
        }
        btnAzul.setOnClickListener {
            onClickAzul()
        }

        puntuacion = 0
        actualizarPuntuación()
        mirar()

    }

    private fun mirar() {
        jugando = false
        //patron.add((1..4).random())
        for (item in patron) {
            GlobalScope.launch {
                delay(1000)
                iluminarBoton(item)
            }
        }
        jugando = true
        contPulsaciones = 0
    }

    private fun iluminarBoton(btn: Int) {
        GlobalScope.launch {
            when (btn) {
                1 -> {
                    var btn: Button = findViewById(R.id.btnVerde)
                    btn.background.setTint(resources.getColor(R.color.greenSelected))
                    delay(1000)
                    btn.background.setTint(resources.getColor(R.color.green))

                }
                2 -> {
                    var btn: Button = findViewById(R.id.btnRojo)
                    btn.background.setTint(resources.getColor(R.color.redSelected))
                    delay(1000)
                    btn.background.setTint(resources.getColor(R.color.red))
                }
                3 -> {
                    var btn: Button = findViewById(R.id.btnAmarillo)
                    btn.background.setTint(resources.getColor(R.color.yellowSelected))
                    delay(1000)
                    btn.background.setTint(resources.getColor(R.color.yellow))
                }
                4 -> {
                    var btn: Button = findViewById(R.id.btnAzul)
                    btn.background.setTint(resources.getColor(R.color.blueSelected))
                    delay(1000)
                    btn.background.setTint(resources.getColor(R.color.blue))
                }
            }
        }
    }

    private fun actualizarPuntuación() {
        val txtPuntuacion: TextView = findViewById(R.id.txtPuntuacion)
        txtPuntuacion.text = puntuacion.toString()
    }

    private fun comprobarPulsacion(seleccion: Int) {
        if (contPulsaciones <= patron.size) {
            if (seleccion == patron[contPulsaciones]) {
                puntuacion++
                actualizarPuntuación()
            } else {
                jugando = false
                perdido = true
                Toast.makeText(contexto, "Perdiste", Toast.LENGTH_SHORT).show()
            }
            contPulsaciones++
        } else {

        }
    }

    fun onClickVerde() {
        if (jugando) {
            comprobarPulsacion(1)
        }
    }

    fun onClickRojo() {
        if (jugando) {
            comprobarPulsacion(2)
        }
    }

    fun onClickAmarillo() {
        if (jugando) {
            comprobarPulsacion(3)
        }
    }

    fun onClickAzul() {
        if (jugando) {
            comprobarPulsacion(4)
        }
    }


}