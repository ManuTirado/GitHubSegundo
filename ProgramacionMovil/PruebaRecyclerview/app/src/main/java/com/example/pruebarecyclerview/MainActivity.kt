package com.example.pruebarecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebarecyclerview.adapter.SuperHeroAdapter
import com.example.pruebarecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        //val manager = GridLayoutManager(this, 2)
        val manager = LinearLayoutManager(this)
        //val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerSuperHero.layoutManager = manager
        binding.recyclerSuperHero.adapter = SuperHeroAdapter(
            SuperHeroProvider.superHeroList
        ) { superHero -> onItemSelected(superHero) }

        //binding.recyclerSuperHero.addItemDecoration(decoration)
    }

    fun onItemSelected(superHero: SuperHero) {
        Toast.makeText(this, superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}