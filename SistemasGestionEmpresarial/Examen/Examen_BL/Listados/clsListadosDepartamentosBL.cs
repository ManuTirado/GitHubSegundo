using Examen_Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen_BL.Listados
{
    /// <summary>
    /// Clase estática que ofrece métodos, también estáticos, para obtener listados de la tabla departamentos
    /// </summary>
    public static class clsListadosDepartamentosBL
    {
        /// <summary>
        /// Función que devuelve un listado con todos los departamentos existentes.
        /// En caso de que no hay ningún departamento, devuelve el listado vacío.
        /// </summary>
        /// <returns>Listado de los departamentos</returns>
        public static List<clsDepartamento> obtenerListadoCompletoBL()
        {
            return (Examen_DAL.Listados.clsListadosDepartamentosDAL.obtenerListadoCompletoDAL());
        }
    }
}
