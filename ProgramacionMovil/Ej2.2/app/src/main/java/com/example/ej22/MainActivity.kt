package com.example.ej22

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

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
        var builder = NotificationCompat.Builder(this, "channelId")
            .setSmallIcon(android.R.drawable.btn_star)
            .setContentTitle("Estado de la aplicación")
            .setContentText("On Start")
            .setColor(Color.BLUE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLights(Color.MAGENTA, 1000, 1000)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setDefaults(Notification.DEFAULT_SOUND)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, builder.build())
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Canal"
            val descriptionText = "Descripcion canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("idCanal", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
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