﻿using Microsoft.Data.SqlClient;
using U10_Ej2.Models.Entidades;

namespace U10_Ej2.Models.DAL
{
    public static class clsListadosPersonas
    {
        public static List<clsPersona> obtenerListadoCompleto()
        {
            SqlConnection miConexion = new SqlConnection();
            List<clsPersona> personas = new List<clsPersona>();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona;
            miConexion.ConnectionString = "server=dhurtado.database.windows.net;database=diegoDB;uid=fernando;pwd=Mandaloriano69;";
            try
            {
                miConexion.Open();

                miComando.CommandText = "SELECT * FROM Personas";
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    while (miLector.Read())
                    {
                        oPersona = new clsPersona();
                        oPersona.ID = (int)miLector["ID"];
                        oPersona.Nombre = (string)miLector["Nombre"];
                        oPersona.Apellidos = (string)miLector["Apellidos"];
                        //Si sospechamos que el campo puede ser Null en la BBDD
                        if (miLector["Telefono"] != System.DBNull.Value)
                        {
                            oPersona.Telefono = (string)miLector["Telefono"];
                        }
                        if (miLector["Direccion"] != System.DBNull.Value)
                        {
                            oPersona.Direccion = (string)miLector["Direccion"];
                        }
                        if (miLector["Foto"] != System.DBNull.Value)
                        {
                            oPersona.Foto = (string)miLector["Foto"];
                        }
                        if (miLector["FechaNacimiento"] != System.DBNull.Value)
                        {
                            oPersona.FechaNacimiento = (DateTime)miLector["FechaNacimiento"];
                        }
                        if (miLector["IDDepartamento"] != System.DBNull.Value)
                        {
                            oPersona.IDDepartamento = (int)miLector["IDDepartamento"];
                        }
                        personas.Add(oPersona);
                    }
                }
                miLector.Close();
                miConexion.Close();
            }
            catch (SqlException exSql)
            {
                throw exSql;
            }
            return personas;
        }
    }
}
