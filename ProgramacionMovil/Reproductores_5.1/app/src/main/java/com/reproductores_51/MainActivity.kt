package com.reproductores_51

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val multimedia = mutableListOf<multimedia>(
            multimedia(
                "Video1",
                "https://file-examples.com/storage/feeb31b1716385276a318de/2017/04/file_example_MP4_480_1_5MG.mp4",
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
        val lvMultimedia = findViewById<ListView>(R.id.lvMultimedia)

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, multimedia)
        lvMultimedia.adapter = arrayAdapter

        lvMultimedia.setOnItemClickListener() { parent, view, position, id ->
            var elemento: multimedia = parent.getItemAtPosition(position) as multimedia
            if (elemento.isVideo) {
                val reproductor = Intent(this, ReproductorVideo::class.java)
                reproductor.putExtra("rutaContenido", elemento.rutaContenido)
                startActivity(reproductor)
            } else {
                val reproductor = Intent(this, ReproductorAudio::class.java)
                reproductor.putExtra("rutaContenido", elemento.rutaContenido)
                reproductor.putExtra("rutaImagen", elemento.rutaImagen)
                startActivity(reproductor)
            }
        }
    }


}




