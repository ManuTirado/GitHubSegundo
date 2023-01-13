using Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DAL
{
    public static class clsManejadoraDepartamento
    {
        /// <summary>
        /// Función que devuelve un clsDepartamento cuyo id coincida con el id pasado por parámetro
        /// Precondiciones: Ninguna
        /// Postcondiciones: Devuelve un clsDepartamento (si no lo encuentra lo crea por el constructor sin parámetros)
        /// </summary>
        /// <param name="id">Id del departamento que buscamos</param>
        /// <returns>clsDepartamento departamento encontrado</returns>
        public static clsDepartamento obtenerDepartamentoPorId (int id)
        {
            foreach (clsDepartamento d in clsListadosDepartamentos.obtenerListadoCompleto())
            {
                if (d.IdDepartamento == id)
                {
                    return d;
                }
            }
            return new clsDepartamento();
        }
    }
}
