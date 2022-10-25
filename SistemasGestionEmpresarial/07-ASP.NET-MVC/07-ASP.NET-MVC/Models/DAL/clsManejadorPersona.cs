namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsManejadorPersona
    {
        public static clsPersona obtenerPersonaPorId(int id)
        {
            clsPersona persona = clsListadoPersonasDAL.obtenerListadoCompletoPersonas().Find(persona => persona.Id == id);
            return persona;
        }

        public static int guardarPersona(clsPersona persona)
        {
            return 0;
        }
    }
}
