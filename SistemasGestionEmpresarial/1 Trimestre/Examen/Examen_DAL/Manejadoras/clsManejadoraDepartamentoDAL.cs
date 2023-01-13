using Examen_DAL.Utilidades;
using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen_DAL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla departamentos de un registro.
    /// Ej: editar un departamento, borrar un departamento, añadir un departamento...
    /// </summary>
    public static class clsManejadoraDepartamentoDAL
    {
        /// <summary>
        /// Elimina un departamento de la tabla Departamentos de la base de datos.
        /// </summary>
        /// <param name="id">ID del departamento a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int eliminarDepartamentoDAL(int id)
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
