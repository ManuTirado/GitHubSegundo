package com.example.simonssays.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simonssays.R

class TaskAdapter(
    val tasks: List<TaskEntity>,
    val checkTask: (TaskEntity) -> Unit,
    val deleteTask: (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvDificultad = view.findViewById<TextView>(R.id.tvDificultad)
        val tvRecord = view.findViewById<TextView>(R.id.tvRecord)
        val tvFechaHora = view.findViewById<TextView>(R.id.tvFechaHora)
        val imgBtnBorrar = view.findViewById<ImageButton>(R.id.imgBtnBorrar)

        fun bind(task: TaskEntity, checkTask: (TaskEntity) -> Unit, deleteTask: (TaskEntity) -> Unit) {
            tvNombre.text = task.nombre
            when (task.dificultad) {
                0 -> tvDificultad.text =  "Fácil"
                1 -> tvDificultad.text =  "Normal"
                2 -> tvDificultad.text =  "Difícil"
                3 -> tvDificultad.text =  "Psicosis"
                else -> tvDificultad.text = "N/A"
            }
            tvRecord.text = task.puntuacion.toString()
            tvFechaHora.text = task.fechaHora
            itemView.setOnClickListener { checkTask(task) }
            imgBtnBorrar.setOnClickListener { deleteTask(task) }
        }
    }
}
