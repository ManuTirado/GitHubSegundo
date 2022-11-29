package com.reproductores_51

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(private val context: Activity, private val arrayList: ArrayList<multimedia>) :
    ArrayAdapter<multimedia>(context, R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item,null)

        val imageView:ImageView = view.findViewById(R.id.ivFotoMultimedia)
        val textView:TextView = view.findViewById(R.id.tvNombreMultimedia)

        imageView.setImageResource(arrayList[position].rutaImagen)
        textView.text = arrayList[position].nombre


        return view
    }
}