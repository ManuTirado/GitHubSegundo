package com.reproductores_51

import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ReproductorVideo : AppCompatActivity() {

    private lateinit var videoView:VideoView
    private lateinit var mediaController:MediaController
    private var stopPosition:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor_video)

        val rutaContenido:String = intent.getStringExtra("rutaContenido")!!

        var rutaVideo:String = rutaContenido

        videoView = findViewById<VideoView>(R.id.vvVideoInternet)
        videoView.setVideoPath(rutaVideo)

        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        videoView.setMediaController(mediaController)
        videoView.requestFocus()
        videoView.setOnCompletionListener {
            Toast.makeText(applicationContext, "Video terminado", Toast.LENGTH_SHORT).show()
        }
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
        }

        videoView.start()
    }

    override fun onPause() {
        super.onPause()
        stopPosition = videoView?.currentPosition!! //stopPosition is an int
        videoView!!.pause()
    }

    override fun onResume() {
        super.onResume()
        videoView?.seekTo(stopPosition)
        videoView?.start() //Or use resume() if it doesn't work. I'm not sure
    }
}

