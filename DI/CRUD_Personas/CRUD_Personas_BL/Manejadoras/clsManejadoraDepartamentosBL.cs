using CRUD_Personas_DAL.Manejadoras;
using CRUD_Personas_Entidades;

namespace CRUD_Personas_BL.Manejadoras
{
    public static class clsManejadoraDepartamentosBL
    {
        public static int EditarDepartamentoDAL(int idDepartamento, clsDepartamento departamentoEditado)
        {
            return (clsManejadoraDepartamentosDAL.EditarDepartamentoDAL(idDepartamento, departamentoEditado));
        }

        public static int InsertarDepartamentoDAL(clsDepartamento departamentoInsertar)
        {
            return (clsManejadoraDepartamentosDAL.InsertarDepartamentoDAL(departamentoInsertar));
        }

        public static int BorrarDepartamentoDAL(int id)
        {
            return (clsManejadoraDepartamentosDAL.BorrarDepartamentoDAL(id));
        }
    }
}
