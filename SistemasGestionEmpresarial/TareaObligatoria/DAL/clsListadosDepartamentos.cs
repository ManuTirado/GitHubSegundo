using Entidades;

namespace DAL
{
    public static class clsListadosDepartamentos
    {
        /// <summary>
        /// Función que devuelve un listado con todos los departamentos
        /// Precondiciones: Ninguna
        /// Postcondiciones: Devuelve un listado de clsDepartamento (vacio o con elementos)
        /// </summary>
        /// <returns>clsDepartamento departamento</returns>
        public static List<clsDepartamento> obtenerListadoCompleto ()
        {
            List<clsDepartamento> departamentos = new List<clsDepartamento> ();
            departamentos.Add(new clsDepartamento(1, "Marketing y Ventas"));
            departamentos.Add(new clsDepartamento(2, "Contabilidad y Finanzas"));
            departamentos.Add(new clsDepartamento(3, "Cadena de Suministro"));
            departamentos.Add(new clsDepartamento(4, "Recursos Humanos"));
            return departamentos;
        }
    }
}