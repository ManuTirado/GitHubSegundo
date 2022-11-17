package com.example.plantillaroom.Room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plantillaroom.R

/**
 * Clase para adaptar la información de la base de datos a una ReciclerView
 */
class TaskAdapter(
    val tasks: List<TaskEntity>,    // Lista a la que le pasaremos los registros de la BD
    val checkTask: (TaskEntity) -> Unit,
    val deleteTask: (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    /**
     *  Wrapper alrededor de la ReciclerView que contiene el diseño de un elemento individual de la lista
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
    }

    /**
     * La inflamos
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_task, parent, false))
    }

    /**
     * Obtenemos el número de elementos
     */
    override fun getItemCount(): Int {
        return tasks.size
    }

    /**
     * Establecemos como y que mostrará cada elemento de la ReciclerView
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvDificultad = view.findViewById<TextView>(R.id.tvDificultad)
        val tvRecord = view.findViewById<TextView>(R.id.tvRecord)
        val tvFechaHora = view.findViewById<TextView>(R.id.tvFechaHora)
        val imgBtnBorrar = view.findViewById<ImageButton>(R.id.imgBtnBorrar)

        fun bind(   // Bindeamos elementos de item_task.xml con los valores de la entidad que queramos
            task: TaskEntity,
            checkTask: (TaskEntity) -> Unit,
            deleteTask: (TaskEntity) -> Unit
        ) {
            tvNombre.text = task.nombre
            when (task.dificultad) {
                0 -> tvDificultad.text = "Fácil"
                1 -> tvDificultad.text = "Normal"
                2 -> tvDificultad.text = "Difícil"
                3 -> tvDificultad.text = "Psicosis"
                else -> tvDificultad.text = "N/A"
            }
            tvRecord.text = task.puntuacion.toString()
            tvFechaHora.text = task.fechaHora
            itemView.setOnClickListener { checkTask(task) }
            imgBtnBorrar.setOnClickListener { deleteTask(task) }
        }
    }
}
