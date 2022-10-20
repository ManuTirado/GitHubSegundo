namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsManejadorPersona
    {
        public static clsPersona obtenerPersonaPorId (int id)
        {
            clsPersona persona = new clsPersona(1, "Manuel", "Tirado", 1);
            return persona;
        }

        public static int guardarPersona(clsPersona persona)
        {
            return 0;
        }
    }
}
