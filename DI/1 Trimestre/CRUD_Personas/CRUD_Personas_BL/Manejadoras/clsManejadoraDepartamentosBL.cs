using CRUD_Personas_DAL.Manejadoras;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_BL.Manejadoras
{
    /// <summary>
    /// Clase estática, con métodos, también estáticos, que permiten hacer modificaciones en la tabla departamentos de un registro.
    /// Ej: editar un departamento, borrar un departamento, añadir un departamento...
    /// </summary>
    public static class clsManejadoraDepartamentosBL
    {
        public static clsDepartamento LeerDepartamentoBL(int id)
        {
            return clsManejadoraDepartamentosDAL.LeerDepartamentoDAL(id);
        }

        /// <summary>
        /// Actualiza un departamento de la tabla Departamentos en la base de datos.
        /// Precondiciones: El nombre no puede ser nulo.
        /// </summary>
        /// <param name="idDepartamento">ID del departamento que se quiere editar</param>
        /// <param name="departamentoEditado">Objeto departamento editado</param>
        /// <returns>Número de filas afectadas (0 si no se ha actualizado o 1 si se ha actualizado)</returns>
        public static int EditarDepartamentoBL(int idDepartamento, clsDepartamento departamentoEditado)
        {
            return (clsManejadoraDepartamentosDAL.EditarDepartamentoDAL(idDepartamento, departamentoEditado));
        }

        /// <summary>
        /// Inserta un departamento en la tabla Departamentos en la base de datos.
        /// Precondiciones: El nombre no puede ser nulo
        /// </summary>
        /// <param name="departamentoInsertar">Objeto departamento que se quiere insertar</param>
        /// <returns>Número de filas afectadas (0 si no se ha insertado o 1 si se ha insertado)</returns>
        public static int InsertarDepartamentoBL(clsDepartamento departamentoInsertar)
        {
            return (clsManejadoraDepartamentosDAL.InsertarDepartamentoDAL(departamentoInsertar));
        }

        /// <summary>
        /// Elimina un departamento de la tabla Departamentos de la base de datos.
        /// </summary>
        /// <param name="id">ID del departamento a eliminar</param>
        /// <returns>Número de filas afectadas (0 si no se ha eliminado o 1 si se ha eliminado)</returns>
        public static int BorrarDepartamentoBL(int id)
        {
            return (clsManejadoraDepartamentosDAL.BorrarDepartamentoDAL(id));
        }
    }
}
