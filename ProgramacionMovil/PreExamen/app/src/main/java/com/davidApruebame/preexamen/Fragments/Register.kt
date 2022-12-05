package com.davidApruebame.preexamen.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.davidApruebame.preexamen.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var comunicador: Comunicador? = null
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var txtUsuario: EditText
    private lateinit var txtContrasena: EditText
    private lateinit var txtNombre: EditText
    private lateinit var txtApellidos: EditText

    private lateinit var user: String
    private lateinit var pass: String
    private lateinit var nombre: String
    private lateinit var apellidos: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onAttach(context: Context) {
        sharedPreferences = context.getSharedPreferences("usersFile", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtNombre = view.findViewById(R.id.txtNombre)
        txtApellidos = view.findViewById(R.id.txtApellidos)
        txtUsuario = view.findViewById(R.id.txtUsuario)
        txtContrasena = view.findViewById(R.id.txtContrasena)
        btnRegister = view.findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            if (txtNombre.text.isNotEmpty() && txtApellidos.text.isNotEmpty() && txtUsuario.text.isNotEmpty() && txtContrasena.text.isNotEmpty()) {
                user = txtNombre.text.toString()
                pass = txtContrasena.text.toString()
                nombre = txtNombre.text.toString()
                apellidos = txtApellidos.text.toString()

                editor.putString("user", user)
                editor.putString("pass", pass)
                editor.putString("nombre", nombre)
                editor.putString("apellidos", apellidos)
                editor.apply()
                Toast.makeText(context,"Registrado?", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "Debe rellenar todos los campos", Toast.LENGTH_SHORT)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Register().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}