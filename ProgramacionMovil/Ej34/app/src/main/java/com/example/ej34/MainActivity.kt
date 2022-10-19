package com.example.ej34

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), comunicador {
    private val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v(TAG, "Main Creado")
    }

    override fun onClickButtonRestar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)
        Log.v(TAG,"onClickButtonRestar(): Valor antes: " + valor.text)
        valor.setText("1")
        Log.v(TAG,"onClickButtonRestar(): Valor despues: " + valor.text)
    }

    override fun onClickButtonSumar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)
        Log.v(TAG,"onClickButtonSumar(): Valor antes: " + valor.toString())
        valor.setText("2")
        Log.v(TAG,"onClickButtonSumar(): Valor despues: " + valor.text)
    }
}