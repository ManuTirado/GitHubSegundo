package com.example.simonssays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simonssays.Room.PuntuacionesApp
import com.example.simonssays.Room.TaskAdapter
import com.example.simonssays.Room.TaskEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Puntuaciones : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        tasks = ArrayList()
        getTasks()
        tasks.sortWith(compareByDescending<TaskEntity>({it.dificultad}).thenByDescending { it.puntuacion }.thenByDescending { it.numeroPulsaciones })

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
            adapter.notifyItemRemoved(position)
        }
    }

    fun deleteAllTask(tasks: List<TaskEntity>) = runBlocking {
        launch {
            for (task in tasks) {
                PuntuacionesApp.database.taskDao()
                    .deleteTask(PuntuacionesApp.database.taskDao().getTaskById(task.id))
            }
        }
    }

    companion object {
        lateinit var tasks: MutableList<TaskEntity>
        lateinit var adapter: TaskAdapter

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

}