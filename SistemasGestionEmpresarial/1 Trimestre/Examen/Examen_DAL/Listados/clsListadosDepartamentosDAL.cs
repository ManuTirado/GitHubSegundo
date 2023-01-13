using Examen_DAL.Utilidades;
using Examen_Entidades;
using Microsoft.Data.SqlClient;

namespace Examen_DAL.Listados
{
    /// <summary>
    /// Clase estática que ofrece métodos, también estáticos, para obtener listados de la tabla departamentos
    /// </summary>
    public static class clsListadosDepartamentosDAL
    {
        /// <summary>
        /// Función que devuelve un listado con todos los departamentos existentes.
        /// En caso de que no hay ningún departamento, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de los departamentos</returns>
        public static List<clsDepartamento> obtenerListadoCompletoDAL()
        {
            SqlConnection miConexion = clsMyConnection.getConnection();
            List<clsDepartamento> departamentos = new List<clsDepartamento>();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsDepartamento oDepartamento;
            try
            {
                miComando.CommandText = "SELECT * FROM Departamentos";
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    while (miLector.Read())
                    {
                        oDepartamento = new clsDepartamento();
                        oDepartamento.ID = (int)miLector["ID"];
                        oDepartamento.Nombre = (string)miLector["Nombre"];
                        departamentos.Add(oDepartamento);
                    }
                }
                miLector.Close();
            }
            catch (SqlException exSql)
            {
                throw exSql;
            }
            finally
            {
                miConexion.Close();
            }
            return departamentos;
        }
    }
}
