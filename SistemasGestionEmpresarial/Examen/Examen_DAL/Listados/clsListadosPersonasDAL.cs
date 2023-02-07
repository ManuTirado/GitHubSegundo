using Examen_DAL.Utilidades;
using Examen_Entidades;
using Microsoft.Data.SqlClient;

namespace Examen_DAL.Listados
{
    /// <summary>
    /// Clase estática que ofrece métodos, también estáticos, para obtener listados de la tabla personas
    /// </summary>
    public static class clsListadosPersonasDAL
    {
        /// <summary>
        /// Función que devuelve un listado con todas las personas existentes.
        /// En caso de que no haya ningúna persona, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de las personas</returns>
        public static List<clsPersona> obtenerListadoCompletoDAL()
        {
            SqlConnection miConexion = clsMyConnection.getConnection();
            List<clsPersona> personas = new List<clsPersona>();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona;
            try
            {
                miComando.CommandText = "SELECT * FROM Personas";
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    while (miLector.Read())
                    {
                        oPersona = new clsPersona();
                        oPersona.id = (int)miLector["ID"];
                        oPersona.nombre = (string)miLector["Nombre"];
                        oPersona.apellidos = (string)miLector["Apellidos"];
                        //Si sospechamos que el campo puede ser Null en la BBDD
                        if (miLector["Telefono"] != System.DBNull.Value)
                        {
                            oPersona.telefono = (string)miLector["Telefono"];
                        }
                        if (miLector["Direccion"] != System.DBNull.Value)
                        {
                            oPersona.direccion = (string)miLector["Direccion"];
                        }
                        if (miLector["Foto"] != System.DBNull.Value)
                        {
                            oPersona.foto = (string)miLector["Foto"];
                        }
                        if (miLector["FechaNacimiento"] != System.DBNull.Value)
                        {
                            oPersona.fechaNacimiento = (DateTime)miLector["FechaNacimiento"];
                        }
                        if (miLector["IDDepartamento"] != System.DBNull.Value)
                        {
                            oPersona.idDepartamento = (int)miLector["IDDepartamento"];
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
            finally
            {
                miConexion.Close();
            }
            return personas;
        }

        /// <summary>
        /// Función que devuelve un listado con todas las personas existentes en un departamento concreto.
        /// En caso de que no haya ningúna persona, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de las personas del departamento pasado</returns>
        public static List<clsPersona> obtenerPersonasDeDepartamentoDAL (int IDDepartamento)
        {
            SqlConnection miConexion = clsMyConnection.getConnection();
            List<clsPersona> personas = new List<clsPersona>();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona;
            try
            {
                miComando.CommandText = "SELECT * FROM Personas WHERE IDDepartamento = " + IDDepartamento + ";";
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    while (miLector.Read())
                    {
                        oPersona = new clsPersona();
                        oPersona.id = (int)miLector["ID"];
                        oPersona.nombre = (string)miLector["Nombre"];
                        oPersona.apellidos = (string)miLector["Apellidos"];
                        //Si sospechamos que el campo puede ser Null en la BBDD
                        if (miLector["Telefono"] != System.DBNull.Value)
                        {
                            oPersona.telefono = (string)miLector["Telefono"];
                        }
                        if (miLector["Direccion"] != System.DBNull.Value)
                        {
                            oPersona.direccion = (string)miLector["Direccion"];
                        }
                        if (miLector["Foto"] != System.DBNull.Value)
                        {
                            oPersona.foto = (string)miLector["Foto"];
                        }
                        if (miLector["FechaNacimiento"] != System.DBNull.Value)
                        {
                            oPersona.fechaNacimiento = (DateTime)miLector["FechaNacimiento"];
                        }
                        if (miLector["IDDepartamento"] != System.DBNull.Value)
                        {
                            oPersona.idDepartamento = (int)miLector["IDDepartamento"];
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
            finally
            {
                miConexion.Close();
            }
            return personas;
        }
    }
}
