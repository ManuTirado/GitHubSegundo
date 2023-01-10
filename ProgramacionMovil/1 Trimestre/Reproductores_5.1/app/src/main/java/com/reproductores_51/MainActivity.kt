package com.reproductores_51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reproductores_51.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val multimedia = arrayListOf(
            multimedia(
                "Video1",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                R.drawable.ic_baseline_ondemand_video_24,
                true
            ),
            multimedia(
                "Audio1",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3",
                R.drawable.carrasquito,
                false
            ),
            multimedia(
                "Video2",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                R.drawable.ic_baseline_ondemand_video_24,
                true
            ),
            multimedia(
                "Audio2",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
                R.drawable.casa4,
                false
            ),
            multimedia(
                "Audio3",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                R.drawable.julia,
                false
            ),
            multimedia(
                "Video3",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
                R.drawable.ic_baseline_ondemand_video_24,
                true
            ),
            multimedia(
                "Audio4",
                "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3",
                R.drawable._3000,
                false
            )
        )

        binding.lvMultimedia.isClickable = true
        binding.lvMultimedia.adapter = MyAdapter(this,multimedia)

        binding.lvMultimedia.setOnItemClickListener() { parent, view, position, id ->
            val elemento: multimedia = parent.getItemAtPosition(position) as multimedia
            if (elemento.isVideo) {
                val reproductor = Intent(this, ReproductorVideo::class.java)
                reproductor.putExtra("rutaContenido", elemento.rutaContenido)
                startActivity(reproductor)
            } else if (!elemento.isVideo) {
                val reproductor = Intent(this, ReproductorAudio::class.java)
                val rutaContenido:String = elemento.rutaContenido
                val rutaImagen:Int = elemento.rutaImagen
                reproductor.putExtra("rutaContenido", rutaContenido)
                reproductor.putExtra("rutaImagen", rutaImagen)
                startActivity(reproductor)
            }
        }
    }


}




