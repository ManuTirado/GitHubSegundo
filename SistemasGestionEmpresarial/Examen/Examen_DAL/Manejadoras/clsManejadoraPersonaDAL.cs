using Examen_DAL.Utilidades;
using Examen_Entidades;
using Microsoft.Data.SqlClient;

namespace Examen_DAL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla personas de un registro.
    /// Ej: editar una persona, borrar una persona, añadir una persona...
    /// </summary>
    public static class clsManejadoraPersonaDAL
    {
        /// <summary>
        /// Actualiza una persona en la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">Id persona a actualizar</param>
        /// <param name="persona">Persona actualizada</param>
        /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
        public static int actualizarPersonaDAL(int id, clsPersona persona)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "UPDATE Personas SET Nombre=@nombre, Apellidos=@apellidos, Telefono=@telefono, Direccion=@direccion, Foto=@foto, FechaNacimiento=@fechaNacimiento, IDDepartamento=@idDepartamento " +
                "WHERE ID=@id;";
            miComando.Parameters.AddWithValue("@id", id);
            miComando.Parameters.AddWithValue("@nombre", persona.nombre);
            miComando.Parameters.AddWithValue("@apellidos", persona.apellidos);
            miComando.Parameters.AddWithValue("@telefono", persona.telefono);
            miComando.Parameters.AddWithValue("@direccion", persona.direccion);
            miComando.Parameters.AddWithValue("@foto", persona.foto);
            miComando.Parameters.AddWithValue("@fechaNacimiento", persona.fechaNacimiento);
            miComando.Parameters.AddWithValue("@idDepartamento", persona.idDepartamento);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        /// <summary>
        /// Elimina una persona de la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">ID de la persona a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int eliminarPersonaDAL(int id)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "DELETE FROM Personas " +
                                    "WHERE ID = @id";
            miComando.Parameters.AddWithValue("@id", id);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        /// <summary>
        /// Inserta una persona en la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="persona">Persona a insertar</param>
        /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
        public static int insertarPersonaDAL(clsPersona persona)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "INSERT INTO Personas (Nombre, Apellidos, Telefono, Direccion, Foto, FechaNacimiento, IDDepartamento) " +
                                    "VALUES (@nombre , @apellidos , @telefono , @direccion , @foto , @fechaNacimiento , @idDepartamento)";
            miComando.Parameters.AddWithValue("@nombre", persona.nombre);
            miComando.Parameters.AddWithValue("@apellidos", persona.apellidos);
            miComando.Parameters.AddWithValue("@telefono", persona.telefono);
            miComando.Parameters.AddWithValue("@direccion", persona.direccion);
            miComando.Parameters.AddWithValue("@foto", persona.foto);
            miComando.Parameters.AddWithValue("@fechaNacimiento", persona.fechaNacimiento);
            miComando.Parameters.AddWithValue("@idDepartamento", persona.idDepartamento);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        /// <summary>
        /// Función que devuelve la persona con el id pasado.
        /// En caso de que no haya ningúna persona, la persona vacía.
        /// </summary>
        /// <returns>Persona</returns>
        public static clsPersona leerPersonaDAL(int id)
        {
            SqlConnection miConexion = clsMyConnection.getConnection();
            clsPersona persona = new clsPersona();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona = new clsPersona();
            try
            {
                miComando.CommandText = "SELECT TOP 1 * FROM Personas WHERE ID = @id";
                miComando.Parameters.AddWithValue("@id", id);
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    miLector.Read();

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
            return oPersona;
        }
    }
}
