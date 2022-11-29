package com.reproductores_51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.reproductores_51.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val multimedia = arrayListOf<multimedia>(
            multimedia(
                "Video1",
                "https://static.videezy.com/system/resources/previews/000/018/948/original/ICON-VERSION8_1.mp4",
                R.drawable.ic_baseline_ondemand_video_24,
                true
            ),
            multimedia(
                "Audio1",
                "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3",
                R.drawable.carrasquito,
                false
            )
        )

        binding.lvMultimedia.isClickable = true
        binding.lvMultimedia.adapter = MyAdapter(this,multimedia)

        binding.lvMultimedia.setOnItemClickListener() { parent, view, position, id ->
            var elemento: multimedia = parent.getItemAtPosition(position) as multimedia
            if (elemento.isVideo) {
                val reproductor = Intent(this, ReproductorVideo::class.java)
                reproductor.putExtra("rutaContenido", elemento.rutaContenido)
                startActivity(reproductor)
            } else if (!elemento.isVideo) {
                val reproductor = Intent(this, ReproductorAudio::class.java)
                var rutaContenido:String = elemento.rutaContenido
                var rutaImagen:Int = elemento.rutaImagen
                reproductor.putExtra("rutaContenido", rutaContenido)
                reproductor.putExtra("rutaImagen", rutaImagen)
                startActivity(reproductor)
            }
        }
    }


}




