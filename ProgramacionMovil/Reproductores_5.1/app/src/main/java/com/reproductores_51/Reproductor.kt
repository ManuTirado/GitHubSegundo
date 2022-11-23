package com.reproductores_51

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class Reproductor : AppCompatActivity() {

    lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reproductor)

        youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube)
        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video
                // into the YouTube Player
                youTubePlayer.loadVideo("nchO8MVHqIo", 0f)
            }
        })
    }

}

