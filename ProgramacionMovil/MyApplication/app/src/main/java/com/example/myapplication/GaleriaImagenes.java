package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class GaleriaImagenes extends AppCompatActivity {
    ImageView imagen;
    Button btnAnterior, btnSiguiente;

    int[] idImagenes = {
            R.drawable.power_amarillo,
            R.drawable.power_azul,
            R.drawable.power_negro,
            R.drawable.power_rojo,
            R.drawable.power_rosa};
    int imagenActual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_imagenes);
        imagenActual=0;

        imagen = findViewById(R.id.imgViewGaleria);
        btnAnterior = findViewById(R.id.btnImgAnterior);
        btnSiguiente = findViewById(R.id.btnImgSiguiente);

        imagen.setImageResource(idImagenes[imagenActual]);
    }

    public void cambiarImagenAnterior (View view) {
        imagenActual--;
        if (imagenActual < 0) {
            imagenActual = idImagenes.length-1;
        }
        imagen.setImageResource(idImagenes[imagenActual]);
    }

    public void cambiarImagenSiguiente (View view) {
        imagenActual++;
        if (imagenActual > idImagenes.length-1) {
            imagenActual = 0;
        }
        imagen.setImageResource(idImagenes[imagenActual]);
    }
}