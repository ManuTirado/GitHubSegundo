using _07_CRUD_Personas_DAL.Conexion;
using CRUD_Personas_Entidades;
using Microsoft.Data.SqlClient;

namespace CRUD_Personas_DAL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla departamentos de un registro.
    /// Ej: editar un departamento, borrar un departamento, añadir un departamento...
    /// </summary>
    public static class clsManejadoraDepartamentosDAL
    {
        public static clsDepartamento LeerDepartamentoDAL(int id)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsDepartamento oDepartamento = null;

            miComando.CommandText = "SELECT * FROM Departamentos WHERE ID = @id";
            miComando.Parameters.AddWithValue("@id", id);

            miComando.Connection = miConexion;

            miLector = miComando.ExecuteReader();

            if (miLector.HasRows)
            {
                miLector.Read();
                oDepartamento = new clsDepartamento();
                oDepartamento.ID = (int)miLector["ID"];
                oDepartamento.Nombre = (string)miLector["Nombre"];
            }
            miLector.Close();
            miConexion.Close();
            return oDepartamento;
        }

        /// <summary>
        /// Actualiza un departamento de la tabla Departamentos en la base de datos.
        /// Precondiciones: El nombre no puede ser nulo.
        /// </summary>
        /// <param name="idDepartamento">ID del departamento que se quiere editar</param>
        /// <param name="departamentoEditado">Objeto departamento editado</param>
        /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
        public static int EditarDepartamentoDAL(int idDepartamento, clsDepartamento departamentoEditado)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "UPDATE Departamentos " +
                                    "SET Nombre = @nombre " +
                                    "WHERE ID = @id";
            miComando.Parameters.AddWithValue("@nombre", departamentoEditado.Nombre);
            miComando.Parameters.AddWithValue("@id", idDepartamento);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        /// <summary>
        /// Inserta un departamento en la tabla Departamentos en la base de datos.
        /// Precondiciones: El nombre no puede ser nulo
        /// </summary>
        /// <param name="departamentoInsertar">Objeto departamento que se quiere insertar</param>
        /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
        public static int InsertarDepartamentoDAL(clsDepartamento departamentoInsertar)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "INSERT INTO Departamentos (Nombre) " +
                                    "VALUES (@nombre)";
            miComando.Parameters.AddWithValue("@nombre", departamentoInsertar.Nombre);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        /// <summary>
        /// Elimina un departamento de la tabla Departamentos de la base de datos.
        /// </summary>
        /// <param name="id">ID del departamento a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int BorrarDepartamentoDAL(int id)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "DELETE FROM Departamentos " +
                                    "WHERE ID = @id";
            miComando.Parameters.AddWithValue("@id", id);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }
    }
}
