package com.example.aplicacionroom


import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.approom.MisNotasApp
import com.example.approom.TaskEntity
import com.example.approom.TasksAdapter
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: TasksAdapter
    lateinit var tasks: MutableList<TaskEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tasks = ArrayList()
        getTasks()
        findViewById<Button>(R.id.btnAddTask).setOnClickListener {
            addTask(TaskEntity(name = findViewById<EditText>(R.id.etTask).text.toString()))
        }
    }

    fun clearFocus() {
        findViewById<EditText>(R.id.etTask).setText("")
    }

    fun Context.hideKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun getTasks() = runBlocking {
        launch {
            tasks = MisNotasApp.database.taskDao().getAllTasks()
            Log.d("PRUEBAS", tasks.toString())
            setUpRecyclerView(tasks)
        }
    }

    fun setUpRecyclerView(tasks: List<TaskEntity>) {
        adapter = TasksAdapter(tasks, { updateTask(it) }, { deleteTask(it) })
        recyclerView = findViewById(R.id.rvTask)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    fun updateTask(task: TaskEntity) = runBlocking {
        launch {
            task.isDone = !task.isDone
            MisNotasApp.database.taskDao().updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) = runBlocking {
        launch {
            val position = tasks.indexOf(task)
            MisNotasApp.database.taskDao().deleteTask(task)
            tasks.remove(task)
            adapter.notifyItemRemoved(position)
        }
    }

    fun addTask(task: TaskEntity) = runBlocking {
        launch {
            val id = MisNotasApp.database.taskDao().addTask(task)
            val recoveryTask = MisNotasApp.database.taskDao().getTaskById(id)
            tasks.add(recoveryTask)
            adapter.notifyItemInserted(tasks.size)
            Log.d("PRUEBAS", id.toString())
            clearFocus()
            hideKeyboard()
        }
    }
}
