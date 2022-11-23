using CRUD_Personas_DAL.Listados;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_BL.Listados
{
    public static class clsListadosPersonasBL
    {
        public static List<clsPersona> ListadoCompletoPersonasBL()
        {
            return (clsListadosPersonasDAL.ListadoCompletoPersonasDAL());
        }
    }
}
