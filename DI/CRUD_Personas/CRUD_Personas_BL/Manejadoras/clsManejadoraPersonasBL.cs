using CRUD_Personas_DAL.Manejadoras;
using CRUD_Personas_Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CRUD_Personas_BL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla personas de un registro.
    /// Ej: editar una persona, borrar una persona, añadir una persona...
    /// </summary>
    public static class clsManejadoraPersonasBL
    {
        /// <summary>
        /// Actualiza una persona de la tabla Personas en la base de datos.
        /// Precondiciones: El nombre y los apellidos no pueden ser nulos.
        /// </summary>
        /// <param name="idPersona">ID de la persona que se quiere editar</param>
        /// <param name="personaEditada">Objeto persona editado</param>
        /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
        public static int EditarPersonaBL(int idPersona, clsPersona personaEditada)
        {
            return (clsManejadoraPersonasDAL.EditarPersonaDAL(idPersona, personaEditada));
        }

        /// <summary>
        /// Inserta una persona en la tabla Personas en la base de datos.
        /// Precondiciones: El nombre y los apellidos no pueden ser nulos.
        /// </summary>
        /// <param name="personaInsertar">Objeto persona que se quiere insertar</param>
        /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
        public static int InsertarPersonaBL(clsPersona personaInsertar)
        {
            return (clsManejadoraPersonasDAL.InsertarPersonaDAL(personaInsertar));
        }

        /// <summary>
        /// Elimina una persona de la tabla Personas de la base de datos.
        /// </summary>
        /// <param name="id">ID de la persona a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int BorrarPersonaBL(int id)
        {
            return (clsManejadoraPersonasDAL.BorrarPersonaDAL(id));
        }
    }
}
