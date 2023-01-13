namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsListadoPersonasDAL
    {
        public static List<clsPersona> obtenerListadoCompletoPersonas()
        {
            List<clsPersona> listadoCompletoPersonas = new List<clsPersona>();
            listadoCompletoPersonas.Add(new clsPersona(1, "Manuel", "Tirado", 1));
            listadoCompletoPersonas.Add(new clsPersona(1, "Álvaro", "Castro", 2));
            listadoCompletoPersonas.Add(new clsPersona(2, "Francisco", "Fresco", 3));
            listadoCompletoPersonas.Add(new clsPersona(3, "Diego", "Urtado", 4));
            listadoCompletoPersonas.Add(new clsPersona(4, "Javier", "Muñoa", 1));
            return listadoCompletoPersonas;
        }
    }
}
