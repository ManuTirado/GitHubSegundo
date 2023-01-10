package com.example.piedrapapeltijeras

import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Comunicador {

    private var jugador: Int = 0 // Selección del jugador (1 = Piedra, 2 = Papel, 3 = Tijeras)
    private var maquina: Int = 0// Selección de la máquina (1 = Piedra, 2 = Papel, 3 = Tijeras)

    private var contGanadasJugador = 0
    private var contGanadasMaquina = 0
    private var contPartidas = 0

    private var jugando: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actualizarContador()
    }

    override fun onClickImgBtnPiedra() {
        if (!jugando) {
            val armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
            armaEscogida.setImageResource(R.drawable.piedra)
            jugador = 1
            seleccionArmaMaquina()
        }
    }

    override fun onClickImgBtnPapel() {
        if (!jugando) {
            val armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
            armaEscogida.setImageResource(R.drawable.papel)
            jugador = 2
            seleccionArmaMaquina()
        }
    }

    override fun onClickImgBtnTijeras() {
        if (!jugando) {
            val armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
            armaEscogida.setImageResource(R.drawable.tijeras)
            jugador = 3
            seleccionArmaMaquina()
        }
    }

    private fun seleccionArmaMaquina() {
        val imgMaquina: ImageView = findViewById(R.id.ArmaMaquina)
        val tiempoAnimacion:Int
        contPartidas++

        when ((1..3).random()) {    // Número aleatorio entre el 1 y el 3
            1 -> {  // Piedra
                imgMaquina.setImageResource(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
                imgMaquina.setBackgroundResource(R.drawable.animacion_piedra)
                val animacion = imgMaquina.background as AnimationDrawable
                tiempoAnimacion = 2000
                animacion.start()
                maquina = 1
            }
            2 -> { // Papel
                imgMaquina.setImageResource(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
                imgMaquina.setBackgroundResource(R.drawable.animacion_papel)
                val animacion: AnimationDrawable = imgMaquina.background as AnimationDrawable
                tiempoAnimacion = 1600
                animacion.start()
                maquina = 2
            }
            3 -> { // Tijeras
                imgMaquina.setImageResource(com.google.android.material.R.color.mtrl_btn_transparent_bg_color)
                imgMaquina.setBackgroundResource(R.drawable.animacion_tijeras)
                val animacion = imgMaquina.background as AnimationDrawable
                tiempoAnimacion = 1800
                animacion.start()
                maquina = 3
            }
            else -> {
                tiempoAnimacion = 1
                println("Algo no salió bien en la selección del arma de la máquina")
            }
        }
        val handler = Handler()
        handler.postDelayed( {  // Espero el tiempo de la animación para continuar la ejecución del programa
            mostrarGanador(comprobarGanador())
        }, tiempoAnimacion.toLong())

    }

    /***
     * Comparo la selección del jugador y de la máquina y según esto muestro quien ha ganado
     */
    private fun mostrarGanador(ganador: Int) {
        val armaEscogida: ImageView = findViewById(R.id.imgArmaEscogida)
        val imgMaquina: ImageView = findViewById(R.id.ArmaMaquina)
        val mensaje: String
        val titulo: String

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

        val builder = AlertDialog.Builder(this).apply {     // Mensaje que solo se mostrará cada 3 partidas
            setTitle("Llevas mucho jugando ya")
            setMessage("Lo mismo deberías dejar de jugar y hacer las actividades de David y eso, no??")
            setNegativeButton("Seguir") { _: DialogInterface, _: Int ->

            }
            setPositiveButton("Cerrar") { _: DialogInterface, _: Int ->
                finish()
            }
            setCancelable(false)
        }

        AlertDialog.Builder(this).apply {
            setTitle(titulo)
            setMessage(mensaje)
            setPositiveButton("OK") { _: DialogInterface, _: Int ->
                armaEscogida.setImageResource(R.drawable.ic_baseline_question_mark_24)
                imgMaquina.setBackgroundResource(R.drawable.ic_baseline_question_mark_24)
                if (contPartidas % 3 == 0)
                {
                    builder.show()  // Cada tres jugadas muestra un mensaje con dos opciones
                }
            }
            setCancelable(false)

        }.show()
        jugando = false
    }

    /***
     * Compruebo las selecciones y según estas devuelvo el ganador (1 = Gana jugador, 2 = Gana máquina, 3 = Empate)
     */
    fun comprobarGanador(): Int {
        val ganador: Int
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

    /***
     * Actualiza la información de los textBox
     */
    private fun actualizarContador() {
        val txtJugador: TextView = findViewById(R.id.txtContJugador)
        val txtMaquina: TextView = findViewById(R.id.txtContMaquina)
        txtJugador.text = contGanadasJugador.toString()
        txtMaquina.text = (contGanadasMaquina.toString())
    }

}
