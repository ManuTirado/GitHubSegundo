package com.example.piedrapapeltijeras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), Comunicador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickImgBtnPiedra() {
        Toast.makeText(this, "Piedra", Toast.LENGTH_SHORT).show()
    }

    override fun onClickImgBtnPapel() {
        Toast.makeText(this, "Papel", Toast.LENGTH_SHORT).show()
    }

    override fun onClickImgBtnTijeras() {
        Toast.makeText(this, "Tijeras", Toast.LENGTH_SHORT).show()
    }
}