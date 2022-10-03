package com.example.ej22

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
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
        setContentView(R.layout.activity_main)
        var builder = NotificationCompat.Builder(this, "idCanal")
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
        val sonido: MediaPlayer = MediaPlayer.create(this,R.raw.sonido_mario)
        sonido.start()
        Log.d(TAG,"Ciclo de vida - onResume")
    }

    override fun onPause() {
        super.onPause()
        setContentView(R.layout.activity_display_message)
        var builder = NotificationCompat.Builder(this, "idCanal2")
            .setSmallIcon(android.R.drawable.ic_delete)
            .setContentTitle("Vuelve a la app ლ(ಠ益ಠლ")
            .setContentText("Por su bien vuelva a la app 💖")
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
            val name = "Canal2"
            val descriptionText = "Descripcion canal"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("idCanal2", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        Log.d(TAG,"Ciclo de vida - onPause")
    }

    override fun onStop() {
        super.onStop()
        val sonido: MediaPlayer = MediaPlayer.create(this,R.raw.fade)
        sonido.start()
        Log.d(TAG,"Ciclo de vida - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Ciclo de vida - onDestroy")
    }

}