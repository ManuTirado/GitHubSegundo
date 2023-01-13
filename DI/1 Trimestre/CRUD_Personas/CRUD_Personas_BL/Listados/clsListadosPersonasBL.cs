using CRUD_Personas_DAL.Listados;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_BL.Listados
{
    /// <summary>
    /// Clase estática que ofrece métodos, también estáticos, para obtener listados de la tabla personas
    /// </summary>
    public static class clsListadosPersonasBL
    {
        /// <summary>
        /// Función que devuelve un listado con todas las personas existentes.
        /// En caso de que no haya ningúna persona, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de las personas</returns>
        public static List<clsPersona> ListadoCompletoPersonasBL()
        {
            return (clsListadosPersonasDAL.ListadoCompletoPersonasDAL());
        }
    }
}
