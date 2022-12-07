using CRUD_Personas_Entidades;

namespace CRUD_Personas_ASP.Models.VM
{
    public class vmListadoDepartamentos
    {
        #region Atributos
        private List<clsDepartamento> listaDepartamentos;
        private clsDepartamento departamentoSeleccionado;
        #endregion

        #region Propiedades
        public List<clsDepartamento> ListaDepartamentos { get { return listaDepartamentos; } }
        public clsDepartamento DepartamentoSeleccionado { get { return departamentoSeleccionado; } set { departamentoSeleccionado = value; } }
        #endregion

        #region Constructores
        public vmListadoDepartamentos()
        {

        }
        public vmListadoDepartamentos(List<clsDepartamento> departamentos, clsDepartamento departamento)
        {
            this.listaDepartamentos = departamentos;
            this.departamentoSeleccionado = departamento;
        }
        #endregion
    }
}
}
