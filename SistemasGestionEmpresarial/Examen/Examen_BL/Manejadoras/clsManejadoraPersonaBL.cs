using Examen_Entidades;
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
        /// Actualiza una persona de la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">ID de la persona a actualizar</param>
        /// <param name="persona">Persona actualizada</param>
        /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
        public static int actualizarPersonaBL(int ID, clsPersona persona)
        {
            return (Examen_DAL.Manejadoras.clsManejadoraPersonaDAL.actualizarPersonaDAL(ID,persona));
        }

        /// <summary>
        /// Elimina una persona de la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">ID de la persona a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int eliminarPersonaBL(int ID) {
            return (Examen_DAL.Manejadoras.clsManejadoraPersonaDAL.eliminarPersonaDAL(ID));
        }

        /// <summary>
        /// Inserta una persona en la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="persona">Persona a insertar</param>
        /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
        public static int insertarPersonaBL(clsPersona persona)
        {
            return (Examen_DAL.Manejadoras.clsManejadoraPersonaDAL.insertarPersonaDAL(persona));
        }

        /// <summary>
        /// Lee una persona en la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">id de la Persona a leer</param>
        /// <returns>Persona con el id pasado o persona vacía si no la encuentra</returns>
        public static clsPersona leerPersonaBL(int id)
        {
            return (Examen_DAL.Manejadoras.clsManejadoraPersonaDAL.leerPersonaDAL(id));
        }
    }


}
