package com.example.ej3_6_app_acceso_datos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.*
import java.util.*

class MainActivity : AppCompatActivity() {
    internal var conn: Connection? = null
    internal var username = "username"
    internal var password = "password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conectarBBDD()
        probarBBDD()
    }

    private fun probarBBDD() {
        var st: Statement? = null

        try {
            val connection: Connection? = conn
            var rs: ResultSet
            if (connection != null) {
                st = connection.createStatement()



                rs = st.executeQuery("SELECT * FROM ad2223_mtirado.Mesa;")
                while (rs.next()) {
                    System.out.println(
                        Toast.makeText(this,rs.getString("IdMesa") + " " + rs.getString("numComensales") + " " + rs.getString("reserva"),Toast.LENGTH_SHORT).show()

                    )
                }

                st.close()
            }
            if (st != null) {
                connection?.close()
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }

    }

    private fun conectarBBDD() {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:" + "mysql" + "://" +
                        "dns11036.phdns11.es" +
                        ":" + "3306" + "/" +
                        "",
                connectionProps
            )
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }
}