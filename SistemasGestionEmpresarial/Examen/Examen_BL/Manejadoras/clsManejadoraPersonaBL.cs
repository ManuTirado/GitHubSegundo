using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen_BL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla personas de un registro.
    /// Ej: editar una persona, borrar una persona, añadir una persona...
    /// </summary>
    public static class clsManejadoraPersonaBL
    {
        /// <summary>
        /// Elimina una persona de la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">ID de la persona a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int eliminarPersonaBL(int ID) {
            return (Examen_DAL.Manejadoras.clsManejadoraPersonaDAL.eliminarPersonaDAL(ID));
        }
    }
}
