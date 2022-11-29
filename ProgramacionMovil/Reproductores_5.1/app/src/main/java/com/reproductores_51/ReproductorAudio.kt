package com.reproductores_51

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.MediaController
import android.widget.SeekBar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.EXTRA_NOTIFICATION_ID
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf

class ReproductorAudio : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    private var mediaplayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_audio)

        val rutaContenido: String = intent.getStringExtra("rutaContenido")!!
        val rutaImagen:Int = intent.getIntExtra("rutaImagen",1)

        var uriAudio = rutaContenido
        val imagenCaratula = findViewById<ImageView>(R.id.ivCaratula)
        imagenCaratula.setImageDrawable(getDrawable(rutaImagen))

        mediaplayer = MediaPlayer.create(this, Uri.parse(uriAudio))
        //mediaplayer  = MediaPlayer.create(this, R.raw.aprieta_carrasquito)

        val seekbar: SeekBar = findViewById(R.id.seekBar)
        seekbar.max = mediaplayer!!.duration

        //Obtenemos los dos botones de la interfaz
        val btnPlay: ImageButton = findViewById(R.id.btnPlayAudio);
        val btnPause: ImageButton = findViewById(R.id.btnPauseAudio);
        //Y les asignamos el controlador de eventos
        btnPlay.setOnClickListener {
            if (!mediaplayer!!.isPlaying) {
                mediaplayer!!.start()
            }
        }
        btnPause.setOnClickListener {
            if (mediaplayer!!.isPlaying) {
                mediaplayer!!.pause()
            }
        }

        // Preparo la seekbar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer!!.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        runnable = Runnable {
            seekbar.progress = mediaplayer!!.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaplayer!!.setOnCompletionListener {
            mediaplayer!!.seekTo(0)
            seekbar.progress = 0
        }
    }

    override fun onDestroy() {
        if (mediaplayer != null) {
            mediaplayer?.stop()
        }
        super.onDestroy()
    }
}