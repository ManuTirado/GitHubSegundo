﻿using Microsoft.Data.SqlClient;

namespace _07_CRUD_Personas_DAL.Conexion
{
    /// <summary>
    /// Esta clase contiene los métodos necesarios para trabajar con el acceso a una base de datos SQL Server.
    /// </summary>
    public static class clsMyConnection
    {
        #region Atributos
        private static string server = "dhurtado.database.windows.net";
        private static string dataBase = "diegoDB";
        private static string user = "fernando";
        private static string pass = "Mandaloriano69";
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
                    connection.ConnectionString = $"server={server};database={dataBase};uid={user};pwd={pass};";
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
