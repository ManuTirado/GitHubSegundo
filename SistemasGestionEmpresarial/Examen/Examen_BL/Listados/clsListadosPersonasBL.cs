using Examen_Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen_BL.Listados
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
        public static List<clsPersona> obtenerListadoCompletoBL()
        {
            return (Examen_DAL.Listados.clsListadosPersonasDAL.obtenerListadoCompletoDAL());
        }

        /// <summary>
        /// Función que devuelve un listado con todas las personas existentes en un departamento concreto.
        /// En caso de que no haya ningúna persona, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de las personas del departamento pasado</returns>
        public static List<clsPersona> obtenerPersonasDeDepartamentoBL(int IDDepartamento)
        {
            return (Examen_DAL.Listados.clsListadosPersonasDAL.obtenerPersonasDeDepartamentoDAL(IDDepartamento));
        }
    }
}
