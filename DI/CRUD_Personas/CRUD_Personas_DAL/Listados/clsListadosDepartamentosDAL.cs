using _07_CRUD_Personas_DAL.Conexion;
using CRUD_Personas_Entidades;
using Microsoft.Data.SqlClient;

namespace CRUD_Personas_DAL.Listados
{
    public static class clsListadosDepartamentosDAL
    {
        public static List<clsDepartamento> ListadoCompletoDepartamentosDAL()
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
                miConexion.Close();
            }
            catch (SqlException exSql)
            {
                throw exSql;
            }
            return departamentos;
        }
    }
}
