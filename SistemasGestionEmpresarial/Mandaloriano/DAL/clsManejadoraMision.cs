using Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public static class clsManejadoraMision
    {
        /// <summary>
        /// Devuelvo la misión de la lista cuyo Id sea igual al pasado por parámetro.
        /// Si no encuentra ninguna coincidencia devuelve un objeto misión creado sin parámetros
        /// Precondiciones: los id de las misiones deben ser únicos
        /// Postcondiciones: nada
        /// </summary>
        /// <param name="misiones">Lista de las misiones</param>
        /// <param name="id">Id de la misión que se quiere obtener</param>
        /// <returns></returns>
        public static clsMision obtenerMisionPorId (List<clsMision> misiones, int id)
        {
            foreach (clsMision m in misiones)
            {
                if (m.Id == id)
                {
                    return m;
                }
            }
            return new clsMision();
        }
    }
}
