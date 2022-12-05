using _07_CRUD_Personas_DAL.Conexion;
using Microsoft.Data.SqlClient;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_DAL.Listados
{
    public static class clsListadosPersonasDAL
    {
        public static List<clsPersona> ListadoCompletoPersonasDAL()
        {
            SqlConnection miConexion = clsMyConnection.getConnection();
            List<clsPersona> personas = new List<clsPersona>();
            SqlCommand miComando = new SqlCommand();
            SqlDataReader miLector;
            clsPersona oPersona;
            try
            {
                miComando.CommandText = "SELECT * FROM Personas";
                miComando.Connection = miConexion;

                miLector = miComando.ExecuteReader();

                if (miLector.HasRows)
                {
                    while (miLector.Read())
                    {
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
                        personas.Add(oPersona);
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
            return personas;
        }
    }
}

