using Microsoft.Data.SqlClient;


namespace Examen_DAL.Utilidades
{
    /// <summary>
    /// Esta clase contiene los métodos necesarios para trabajar con el acceso a una base de datos SQL Server.
    /// </summary>
    public static class clsMyConnection
    {
        #region Atributos
        private static string server = $"DESKTOP-BJVTTF1";
        private static string dataBase = "PruebaExamen";
        private static string user = "prueba";
        private static string pass = "123";
        #endregion

        #region METODOS
        /// <summary>
        /// Método que abre una conexión con la base de datos
        /// </summary>
        /// <pre>Nada.</pre>
        /// <returns>Una conexión abierta con la base de datos</returns>
        public static SqlConnection getConnection()
        {
            SqlConnection connection = new SqlConnection();
            {
                try
                {
                    connection.ConnectionString = $"server={server};database={dataBase};uid={user};pwd={pass};TrustServerCertificate = True";
                    connection.Open();
                }
                catch (SqlException)
                {
                    throw;
                }
            }
            return connection;
        }

        /// <summary>
        /// Este metodo cierra una conexión con la Base de datos
        /// </summary>
        /// <post>La conexion es cerrada</post>
        /// <param name="connection">SqlConnection pr referencia. Conexion a cerrar
        /// </param>
        public static void closeConnection(ref SqlConnection connection)
        {
            try
            {
                connection.Close();
            }
            catch (SqlException)
            {
                throw;
            }
            catch (InvalidOperationException)
            {
                throw;
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
    #endregion
}
