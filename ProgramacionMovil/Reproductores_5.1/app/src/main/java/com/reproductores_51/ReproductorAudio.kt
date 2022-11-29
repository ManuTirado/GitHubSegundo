package com.reproductores_51

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import java.io.IOException

@Suppress("DEPRECATION")
class ReproductorAudio : AppCompatActivity() {

    private lateinit var mediaplayer: MediaPlayer
    private lateinit var seekbar: SeekBar

    private lateinit var tvCurrentTime: TextView
    private lateinit var tvTotalDuration: TextView

    private lateinit var btnPlayPause: ImageButton

    private lateinit var imagenCaratula: ImageView

    private var handler = Handler()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_audio)

        val rutaImagen: Int = intent.getIntExtra("rutaImagen", 1)

        //Obtenemos los elementos de la interfaz
        btnPlayPause = findViewById(R.id.btnPlayPauseAudio)
        imagenCaratula = findViewById(R.id.ivCaratula)
        seekbar = findViewById(R.id.seekBar)
        tvCurrentTime = findViewById(R.id.tvCurrentTime)
        tvTotalDuration = findViewById(R.id.tvTotalDuration)

        mediaplayer = MediaPlayer()
        seekbar.max = 100

        //Asigno los eventos de click en los botones
        btnPlayPause.setOnClickListener {
            if (mediaplayer.isPlaying) {
                handler.removeCallbacks(updater)
                mediaplayer.pause()
                btnPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            } else {
                mediaplayer.start()
                btnPlayPause.setImageResource(R.drawable.ic_baseline_pause_24)
                updateSeekBar()
            }
        }

        prepareMediaplayer()

        imagenCaratula.setImageDrawable(getDrawable(rutaImagen))

        seekbar.setOnTouchListener(OnTouchListener { view, motionEvent ->
            val seekBar1 = view as SeekBar
            val playPosition = mediaplayer.duration / 100 * seekBar1.progress
            mediaplayer.seekTo(playPosition)
            tvCurrentTime.text = milliSecondsToTimer(mediaplayer.currentPosition.toLong())
            false
        })

        mediaplayer.setOnBufferingUpdateListener { mediaPlayer, i ->
            seekbar.secondaryProgress = i
        }

        mediaplayer.setOnCompletionListener { mediaplayer ->
            seekbar.progress = 0
            btnPlayPause.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            tvCurrentTime.setText(R.string.zero)
            tvTotalDuration.setText(R.string.zero)
            mediaplayer.reset()
            prepareMediaplayer()
        }
    }

    private fun prepareMediaplayer() {
        try {
            mediaplayer.setDataSource(intent.getStringExtra("rutaContenido"))
            mediaplayer.prepare()
            tvTotalDuration.setText(milliSecondsToTimer(mediaplayer.duration.toLong()))
        } catch (e: IOException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private val updater = Runnable {
        updateSeekBar()
        val currentDuration = mediaplayer.currentPosition.toLong()
        tvCurrentTime.setText(milliSecondsToTimer(currentDuration))
    }

    private fun updateSeekBar() {
        if (mediaplayer.isPlaying) {
            seekbar.progress =
                ((mediaplayer.currentPosition.toFloat() / mediaplayer.duration * 100).toInt())
            handler.postDelayed(updater, 1000)
        }
    }

    private fun milliSecondsToTimer(milliseconds: Long): String {
        var timerString = ""
        val secondsString: String

        val hours = (milliseconds / (1000 * 60 * 60))
        val minutes = (milliseconds % (1000 * 60 * 60)) / (1000 * 60)
        val seconds = ((milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000))

        if (hours > 0) {
            timerString = "$hours:"
        }
        if (seconds < 10) {
            secondsString = "0$seconds"
        } else {
            secondsString = seconds.toString()
        }

        timerString = "$timerString$minutes:$secondsString"
        return timerString
    }

    override fun onDestroy() {
        if (mediaplayer.isPlaying) {
            mediaplayer.pause()
            mediaplayer.stop()
        }
        super.onDestroy()
    }
}