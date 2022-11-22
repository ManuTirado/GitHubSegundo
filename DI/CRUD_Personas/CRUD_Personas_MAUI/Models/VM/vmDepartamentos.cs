using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmDepartamentos
    {
        List<clsDepartamento> listaDepartamentos;
        List<clsDepartamento> listaDepartamentosBackup;
        clsDepartamento departamentoSeleccionado;

        DelegateCommand eliminarDepartamento;
        DelegateCommand buscarDepartamento;
        DelegateCommand editarDepartamento;
    }
}
