package com.example.piedrapapeltijeras

import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.Error

class MainActivity : AppCompatActivity(), Comunicador {

    private var jugador: Int = 0
    private var maquina: Int = 0

    private var contGanadasJugador = 0
    private var contGanadasMaquina = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actualizarContador()
    }

    override fun onClickImgBtnPiedra() {
        var armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
        armaEscogida.setImageResource(R.drawable.piedra)
        jugador = 1
        seleccionArmaMaquina()
    }

    override fun onClickImgBtnPapel() {
        var armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
        armaEscogida.setImageResource(R.drawable.papel)
        jugador = 2
        seleccionArmaMaquina()
    }

    override fun onClickImgBtnTijeras() {
        var armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
        armaEscogida.setImageResource(R.drawable.tijeras)
        jugador = 3
        seleccionArmaMaquina()
    }

    fun seleccionArmaMaquina() {
        var imgMaquina: ImageView = findViewById(R.id.ArmaMaquina)
        var aleatorio: Int = (1..3).random()
        when (aleatorio) {
            1 -> {
                imgMaquina.setImageResource(R.drawable.piedra)
                maquina = 1
            }
            2 -> {
                imgMaquina.setImageResource(R.drawable.papel)
                maquina = 2
            }
            3 -> {
                imgMaquina.setImageResource(R.drawable.tijeras)
                maquina = 3
            }
            else -> {
                println("Algo no salió bien en la selección del arma de la máquina")
            }
        }

        mostrarGanador(comprobarGanador())
    }


    private fun mostrarGanador(ganador: Int) {
        var armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
        var imgMaquina: ImageView = findViewById(R.id.ArmaMaquina)
        var mensaje: String
        var titulo: String
        when (ganador) {
            1 -> {
                titulo = "Ganador"
                mensaje = "Ha ganado!! 🥵🔥🔥"
            }
            2 -> {
                titulo = "Perdedor"
                mensaje = "Ha perdido 😭😭💔"
            }
            3 -> {
                titulo = "Empate"
                mensaje = "Nadie gana, es decir no ganas"
            }
            else -> {
                titulo = "XD'nt"
                mensaje = "Algo salió mal"
            }
        }
        AlertDialog.Builder(this).apply {
            setTitle(titulo)
            setMessage(mensaje)
            setPositiveButton("OK") { dialogInerface: DialogInterface, i: Int ->
                armaEscogida.setImageResource(R.drawable.ic_baseline_question_mark_24)
                imgMaquina.setImageResource(R.drawable.ic_baseline_question_mark_24)
            }
        }.show()
    }

    fun comprobarGanador(): Int {
        var ganador: Int
        if (jugador == maquina) {
            ganador = 3
        } else if ((jugador == 1 && maquina == 3) || (jugador == 2 && maquina == 1) || (jugador == 3 && maquina == 2)) {
            ganador = 1
            contGanadasJugador++
        } else {
            ganador = 2
            contGanadasMaquina++
        }
        actualizarContador()
        return ganador
    }

    private fun actualizarContador() {
        var txtJugador: TextView = findViewById(R.id.txtContJugador)
        var txtMaquina: TextView = findViewById(R.id.txtContMaquina)
        txtJugador.setText(contGanadasJugador.toString())
        txtMaquina.setText(contGanadasMaquina.toString())
    }

}
