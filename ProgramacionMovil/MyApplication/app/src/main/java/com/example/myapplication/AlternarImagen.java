package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AlternarImagen extends AppCompatActivity {
    ImageButton imgBtnAlternar;

    int sonido = R.drawable.ic_baseline_volume_up_24;
    int mute = R.drawable.ic_baseline_volume_off_24;

    boolean encendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternar_imagen);

        imgBtnAlternar = findViewById(R.id.imgBtnMuteUnmute);

        encendido = true;
        imgBtnAlternar.setImageResource(sonido);
    }

    public void alternarImagen (View view) {
        if (encendido) {
            imgBtnAlternar.setImageResource(mute);
            encendido = false;
        } else {
            imgBtnAlternar.setImageResource(sonido);
            encendido = true;
        }
    }
}