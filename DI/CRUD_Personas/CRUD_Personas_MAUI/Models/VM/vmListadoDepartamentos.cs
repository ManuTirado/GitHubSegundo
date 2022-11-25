using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmListadoDepartamentos
    {
        private List<clsDepartamento> listaDepartamentosBackup;
        public List<clsDepartamento> listaDepartamentos;
        clsDepartamento departamentoSeleccionado;

        DelegateCommand eliminarDepartamento;
        DelegateCommand anadirDepartamento;
        DelegateCommand editarDepartamento;
    }
}
