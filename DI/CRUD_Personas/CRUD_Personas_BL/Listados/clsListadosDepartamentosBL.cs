using CRUD_Personas_DAL.Listados;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_BL.Listados
{
    public static class clsListadosDepartamentosBL
    {
        public static List<clsDepartamento> ListadoCompletoDepartamentosBL()
        {
            return (clsListadosDepartamentosDAL.ListadoCompletoDepartamentosDAL());
        }
    }
}
