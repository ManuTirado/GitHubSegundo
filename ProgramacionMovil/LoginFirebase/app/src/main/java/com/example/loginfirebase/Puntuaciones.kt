package com.example.loginfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginfirebase.LoginActivity.Companion.db
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Puntuaciones : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones)

        val actionBar = supportActionBar
        actionBar!!.title = "Puntuaciones"
        actionBar.setDisplayHomeAsUpEnabled(true)

        tasks = ArrayList()
        getTasks()
    }

    private fun getTasks() {
        db.collection("puntuaciones").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Puntuaciones", "Puntuaciones (tamaño): ${document.size()}")
                    rellenarListado(document)
                    //usuario = document.get("nombreUsuario").toString()
                } else {
                    Log.d("Puntuaciones", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Puntuaciones", "get failed with ", exception)
            }
    }

    private fun rellenarListado(puntuaciones: QuerySnapshot) {
        puntuaciones.documents.forEach { p ->
            tasks.add(
                TaskEntity(
                    email = p.id,
                    nombre = p.get("nombre").toString(),
                    dificultad = p.get("dificultad").toString().toInt(),
                    puntuacion = p.get("puntuacion").toString().toInt(),
                    numeroPulsaciones = p.get("numeroPulsaciones").toString().toInt(),
                    fechaHora = p.get("fechaHora").toString()
                )
            )
        }
        tasks.sortWith(compareByDescending<TaskEntity> { it.dificultad }
            .thenByDescending { it.puntuacion }
            .thenByDescending { it.numeroPulsaciones })
        setUpRecyclerView(tasks)
    }

    private fun setUpRecyclerView(tasks: List<TaskEntity>) {
        adapter = TaskAdapter(tasks, { updateTask(it) }, { deleteTask(it) })
        recyclerView = findViewById(R.id.rvTask)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun updateTask(task: TaskEntity) = runBlocking {
        launch {
            //PuntuacionesApp.database.taskDao().updateTask(task)
        }
    }

    private fun deleteTask(task: TaskEntity) = runBlocking {
        launch {
            val position = tasks.indexOf(task)
            if (task.email == HomeActivity.email) {
                db.collection("puntuaciones").document(task.email)
                    .delete()
                    .addOnSuccessListener {
                        tasks.remove(task)
                        adapter.notifyItemRemoved(position)
                        Toast.makeText(recyclerView.context, "Borrado!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            recyclerView.context,
                            "Error borrando ;(",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(
                    recyclerView.context,
                    "No puede eliminar una puntuación ajena",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun deleteAllTask(tasks: List<TaskEntity>) = runBlocking {
        /*
        launch {
            for (task in tasks) {
                PuntuacionesApp.database.taskDao()
                    .deleteTask(PuntuacionesApp.database.taskDao().getTaskById(task.id))
            }
        }
         */
    }

    companion object {
        lateinit var tasks: MutableList<TaskEntity>
        lateinit var adapter: TaskAdapter

        fun addTask(task: TaskEntity) = runBlocking {
            //launch {
                //val id = PuntuacionesApp.database.taskDao().addTask(task)
                //val recoveryTask = PuntuacionesApp.database.taskDao().getTaskById(id.toInt())
                //tasks.add(recoveryTask)
                //adapter.notifyItemInserted(tasks.size)
                //Log.d("PRUEBAS", id.toString())
            //}
        }
    }

}