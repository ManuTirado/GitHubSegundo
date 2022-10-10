package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open,R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                    {
                        Toast.makeText(MainActivity.this,"Home seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.calculadora:
                    {
                        Toast.makeText(MainActivity.this,"Calculadora seleccionada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (MainActivity.this, Sumar.class);
                        startActivityForResult(intent, 0);
                        break;
                    }
                    case R.id.color_texto:
                    {
                        Toast.makeText(MainActivity.this,"Color texto seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.alinear_texto:
                    {
                        Toast.makeText(MainActivity.this,"Alinear texto seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.galeria:
                    {
                        Toast.makeText(MainActivity.this,"Galería seleccionada", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.tamano_texto:
                    {
                        Toast.makeText(MainActivity.this,"Tamaño texto seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.altavoz:
                    {
                        Toast.makeText(MainActivity.this,"Altavoz seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case R.id.formatear_texto:
                    {
                        Toast.makeText(MainActivity.this,"Formato texto seleccionado", Toast.LENGTH_SHORT).show();
                        break;
                    }

                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
