using CRUD_Personas_Entidades;

namespace ApiAsincrona_BL
{
    public class clsListadosPersonasBL
    {
        public static async Task<List<clsPersona>> ListadoCompletoPersonasBL()
        {
            return await ApiAsincrona_DAL.clsListadosPersonasDAL.ListadoCompletoPersonasDAL();
        }
}