package com.reproductores_51

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.SeekBar

class ReproductorAudio : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    private var mediaplayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_audio)
        var uriAudio = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"

        mediaplayer = MediaPlayer.create(this, Uri.parse(uriAudio))
        //mediaplayer  = MediaPlayer.create(this, R.raw.aprieta_carrasquito)

        val seekbar:SeekBar = findViewById(R.id.seekBar)
        seekbar.max = mediaplayer!!.duration

        //Obtenemos los tres botones de la interfaz
        val btnPlay:ImageButton = findViewById(R.id.btnPlayAudio);
        val btnPause:ImageButton = findViewById(R.id.btnPauseAudio);
        //Y les asignamos el controlador de eventos
        btnPlay.setOnClickListener {
            if (!mediaplayer!!.isPlaying) {
                mediaplayer!!.start()
            }
        }
        btnPause.setOnClickListener{
            if (mediaplayer!!.isPlaying) {
                mediaplayer!!.pause()
            }
        }

        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer!!.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
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
}