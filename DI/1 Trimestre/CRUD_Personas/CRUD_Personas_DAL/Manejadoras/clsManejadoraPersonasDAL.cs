using _07_CRUD_Personas_DAL.Conexion;
using CRUD_Personas_Entidades;
using Microsoft.Data.SqlClient;

namespace CRUD_Personas_DAL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla personas de un registro.
    /// Ej: editar una persona, borrar una persona, añadir una persona...
    /// </summary>
    public static class clsManejadoraPersonasDAL
    {
        public static clsPersona LeerPersonaDAL(int id)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona = null;

            miComando.CommandText = "SELECT * FROM Personas WHERE ID = @id";
            miComando.Parameters.AddWithValue("@id", id);

            miComando.Connection = miConexion;

            miLector = miComando.ExecuteReader();

            if (miLector.HasRows)
            {
                miLector.Read();
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
            }
            miLector.Close();
            miConexion.Close();
            return oPersona;
        }

    /// <summary>
    /// Actualiza una persona de la tabla Personas en la base de datos.
    /// Precondiciones: El nombre y los apellidos no pueden ser nulos.
    /// </summary>
    /// <param name="idPersona">ID de la persona que se quiere editar</param>
    /// <param name="personaEditada">Objeto persona editado</param>
    /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
    public static int EditarPersonaDAL(int idPersona, clsPersona personaEditada)
    {
        int columnasAfectadas;
        SqlConnection miConexion = clsMyConnection.getConnection();
        SqlCommand miComando = new SqlCommand();

        miComando.CommandText = "UPDATE Personas " +
                                "SET Nombre = @nombre, " +
                                "Apellidos = @apellidos, " +
                                "Telefono = @telefono, " +
                                "Direccion  = @direccion, " +
                                "Foto  = @foto, " +
                                "FechaNacimiento  = @fechaNacimiento, " +
                                "IDDepartamento  = @idDepartamento " +
                                "WHERE ID = @id";
        miComando.Parameters.AddWithValue("@nombre", (string.IsNullOrEmpty(personaEditada.Nombre) ? null : personaEditada.Nombre));
        miComando.Parameters.AddWithValue("@apellidos", (string.IsNullOrEmpty(personaEditada.Apellidos) ? null : personaEditada.Apellidos));
        miComando.Parameters.AddWithValue("@telefono", personaEditada.Telefono);
        miComando.Parameters.AddWithValue("@direccion", personaEditada.Direccion);
        miComando.Parameters.AddWithValue("@foto", personaEditada.Foto);
        miComando.Parameters.AddWithValue("@fechaNacimiento", personaEditada.FechaNacimiento);
        miComando.Parameters.AddWithValue("@idDepartamento", personaEditada.IDDepartamento);
        miComando.Parameters.AddWithValue("@id", idPersona);

        miComando.Connection = miConexion;

        columnasAfectadas = miComando.ExecuteNonQuery();
        return columnasAfectadas;
    }

    /// <summary>
    /// Inserta una persona en la tabla Personas en la base de datos.
    /// Precondiciones: El nombre y los apellidos no pueden ser nulos.
    /// </summary>
    /// <param name="personaInsertar">Objeto persona que se quiere insertar</param>
    /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
    public static int InsertarPersonaDAL(clsPersona personaInsertar)
    {
        int columnasAfectadas;
        SqlConnection miConexion = clsMyConnection.getConnection();
        SqlCommand miComando = new SqlCommand();

        miComando.CommandText = "INSERT INTO Personas (Nombre, Apellidos, Telefono, Direccion, Foto, FechaNacimiento, IDDepartamento) " +
                                "VALUES (@nombre, @apellidos, @telefono, @direccion, @foto, @fechaNacimiento, @idDepartamento)";
        miComando.Parameters.AddWithValue("@nombre", personaInsertar.Nombre);
        miComando.Parameters.AddWithValue("@apellidos", personaInsertar.Apellidos);
        miComando.Parameters.AddWithValue("@telefono", (string.IsNullOrEmpty(personaInsertar.Telefono) ? "NULL" : personaInsertar.Telefono));
        miComando.Parameters.AddWithValue("@direccion", (string.IsNullOrEmpty(personaInsertar.Direccion) ? "NULL" : personaInsertar.Direccion));
        miComando.Parameters.AddWithValue("@foto", (string.IsNullOrEmpty(personaInsertar.Foto) ? "NULL" : personaInsertar.Foto));
        miComando.Parameters.AddWithValue("@fechaNacimiento", personaInsertar.FechaNacimiento);
        miComando.Parameters.AddWithValue("@idDepartamento", personaInsertar.IDDepartamento);

        miComando.Connection = miConexion;

        columnasAfectadas = miComando.ExecuteNonQuery();
        return columnasAfectadas;
    }

    /// <summary>
    /// Elimina una persona de la tabla Personas de la base de datos.
    /// </summary>
    /// <param name="id">ID de la persona a eliminar</param>
    /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
    public static int BorrarPersonaDAL(int id)
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
}
}
