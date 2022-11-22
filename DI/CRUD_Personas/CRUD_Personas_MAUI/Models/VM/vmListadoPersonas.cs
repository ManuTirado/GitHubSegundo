using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmListadoPersonas
    {
        private List<clsDepartamento> listaDepartamentos;
        List<clsPersonaNombreDepartamento> listaPersonas;
        private List<clsPersona> listaPersonasBackup;
        clsPersona personaSeleccionado;
        string busquedaUsuario;
        DelegateCommand eliminarPersona;
        DelegateCommand buscarPersona;
        DelegateCommand editarPersona;
    }
}
