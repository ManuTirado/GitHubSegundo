package com.example.plantillaroom.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantillaroom.MainActivity
import com.example.plantillaroom.R
import com.example.plantillaroom.Room.Puntuaciones.Companion.tasks
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Puntuaciones : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        // Meto los registros
        tasks = ArrayList()
        getTasks()
        addTask(
            TaskEntity(
                nombre = "nombre",
                dificultad = 1,
                puntuacion = 1,
                numeroPulsaciones = 3,
                //fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/uu kk:mm"))
                fechaHora = "dd/MM"
            )
        )
    }

    /**
     * Introduce todos los registros de la BD en la lista tasks.
     * Para ello accedemos a la DAO y obtenemos todos los registros
     */
    fun getTasks() = runBlocking {
        launch {
            tasks = PuntuacionesApp.database.taskDao().getAllTasks()
            setUpRecyclerView(tasks)    // Seteo la ReciclerView con los elementos de tasks
        }
    }

    /**
     * Seteo la ReciclerView con los elementos de tasks
     */
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

    companion object {  // Hago la lista, el adaptador y addTask "estáticos para que se puedan llamar desde otros sitios, pero si solo lo uso aquí habría que quitar companion object
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