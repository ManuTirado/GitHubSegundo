using _07_CRUD_Personas_DAL.Conexion;
using Microsoft.Data.SqlClient;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_DAL.Listados
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
        public static List<clsPersona> ListadoCompletoPersonasDAL()
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
            finally
            {
                miConexion.Close();
            }
            return personas;
        }
    }
}

