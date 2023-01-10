package com.example.ejerpracticou2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity - Manuel Tirado García:"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"Ciclo de vida - onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG,"Ciclo de vida - onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"Ciclo de vida - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"Ciclo de vida - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"Ciclo de vida - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"Ciclo de vida - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Ciclo de vida - onDestroy")
    }

}