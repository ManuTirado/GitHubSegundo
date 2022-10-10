package com.example.ej31v2

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.ActionBarDrawerToggle
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var drawerToggle: ActionBarDrawerToggle
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    Toast.makeText(this@MainActivity, "Home seleccionado", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.calculadora -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Calculadora seleccionada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.color_texto -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Color texto seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.alinear_texto -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Alinear texto seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.galeria -> {
                    Toast.makeText(this@MainActivity, "Galería seleccionada", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.tamano_texto -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Tamaño texto seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.altavoz -> {
                    Toast.makeText(this@MainActivity, "Altavoz seleccionado", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.formatear_texto -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Formato texto seleccionado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            false
        })
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        super.onBackPressed()
    }
}