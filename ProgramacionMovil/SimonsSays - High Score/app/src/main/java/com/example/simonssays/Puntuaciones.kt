package com.example.simonssays

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.simonssays.Room.PuntuacionesApp
import com.example.simonssays.Room.TaskAdapter
import com.example.simonssays.Room.TaskEntity
import com.example.simonssays.Room.TasksDatabase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime

class Puntuaciones : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TaskAdapter
    lateinit var tasks: MutableList<TaskEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        tasks = ArrayList()
        getTasks()

        addTask(TaskEntity(nombre = "Lolo", dificultad = "Fasi", puntuacion = 1, numeroPulsaciones = 3, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Kaki", dificultad = "Fasi", puntuacion = 2, numeroPulsaciones = 7, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Fresh Co", dificultad = "Difisi", puntuacion = 3, numeroPulsaciones = 1, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Colombi", dificultad = "Medio", puntuacion = 2, numeroPulsaciones = 6, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Caramelo", dificultad = "Fasi", puntuacion = 4, numeroPulsaciones = 7, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Sinkeh", dificultad = "Difisi", puntuacion = 1, numeroPulsaciones = 12, fechaHora = LocalDateTime.now().toString()))
        addTask(TaskEntity(nombre = "Munoa", dificultad = "Fasi", puntuacion = 2, numeroPulsaciones = 53, fechaHora = LocalDateTime.now().toString()))
    }

    fun getTasks() = runBlocking {
        launch {
            tasks = PuntuacionesApp.database.taskDao().getAllTasks()
            setUpRecyclerView(tasks)
        }
    }

    fun setUpRecyclerView(tasks: List<TaskEntity>) {
        adapter = TaskAdapter(tasks, { updateTask(it) }, { deleteTask(it) })
        recyclerView = findViewById(R.id.rvTask)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun updateTask(task: TaskEntity) = runBlocking {
        launch {
            PuntuacionesApp.database.taskDao().updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) = runBlocking {
        launch {
            val position = tasks.indexOf(task)
            PuntuacionesApp.database.taskDao().deleteTask(task)
            tasks.remove(task)
            adapter.notifyItemRemoved (position)
        }
    }

    fun addTask(task: TaskEntity) = runBlocking {
        launch {
            val id = PuntuacionesApp.database.taskDao().addTask(task)
            val recoveryTask = PuntuacionesApp.database.taskDao().getTaskById(id.toInt())
            tasks.add(recoveryTask)
            adapter.notifyItemInserted(tasks.size)
            Log.d("PRUEBAS", id.toString())
        }
    }
}