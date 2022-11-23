using _07_CRUD_Personas_DAL.Conexion;
using CRUD_Personas_Entidades;
using Microsoft.Data.SqlClient;

namespace CRUD_Personas_DAL.Manejadoras
{
    public static class clsManejadoraDepartamentosDAL
    {
        public static int EditarDepartamentoDAL(int idDepartamento, clsDepartamento departamentoEditado)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "UPDATE Personas " +
                                    "SET Nombre = @nombre" +
                                    "WHERE ID = @id";
            miComando.Parameters.AddWithValue("@nombre", departamentoEditado.Nombre);
            miComando.Parameters.AddWithValue("@id", idDepartamento);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        public static int InsertarDepartamentoDAL(clsDepartamento departamentoInsertar)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "INSERT INTO Personas (Nombre) " +
                                    "VALUES (@nombre)";
            miComando.Parameters.AddWithValue("@nombre", departamentoInsertar.Nombre);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }

        public static int BorrarDepartamentoDAL(int id)
        {
            int columnasAfectadas;
            SqlConnection miConexion = clsMyConnection.getConnection();
            SqlCommand miComando = new SqlCommand();

            miComando.CommandText = "DELETE FROM Personas (Nombre) " +
                                    "WHERE ID = @id";
            miComando.Parameters.AddWithValue("@id", id);

            miComando.Connection = miConexion;

            columnasAfectadas = miComando.ExecuteNonQuery();
            return columnasAfectadas;
        }
    }
}
