using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmEditarInsertarPersona
    {
        clsPersona personaSeleccionada;
        List<clsDepartamento> listaDepartmentos;
        DelegateCommand guardarPersona; // Diferenciar entre inserción o actualización
    }
}
