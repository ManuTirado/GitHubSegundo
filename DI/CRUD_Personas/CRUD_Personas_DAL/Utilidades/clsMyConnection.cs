using Microsoft.Data.SqlClient;

// Esta clase contiene los métodos necesarios para trabajar con el acceso a una base de datos SQL Server
//PROPIEDADES
//   _server: cadena 
//   _database: cadena, básica. Consultable/modificable.
//   _user: cadena, básica. Consultable/modificable.
//   _pass: cadena, básica. Consultable/modificable.

// MÉTODOS
//   Function getConnection() As SqlConnection
//       Este método abre una conexión con la base de datos. Lanza excepciones de tipo: SqlExcepion, InvalidOperationException y Exception.
//   
//   Sub closeConnection(ByRef connection As SqlConnection)
//       Este método cierra una conexión con la base de datos. Lanza excepciones de tipo: SqlExcepion, InvalidOperationException y Exception.
//


namespace _07_CRUD_Personas_DAL.Conexion
{
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
            bool conectado = false;
            for (int i = 0; i < 10 && !conectado; i++)
            {
                try
                {
                    connection.ConnectionString = $"server={server};database={dataBase};uid={user};pwd={pass};";
                    //connection.ConnectionString = "server=dhurtado.database.windows.net;database=diegoDB;uid=fernando;pwd=Mandaloriano69;";
                    connection.Open();
                    conectado = true;
                }
                catch (SqlException)
                {
                    conectado=false;
                    if (i >= 9)
                    {
                        throw;
                    }
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
