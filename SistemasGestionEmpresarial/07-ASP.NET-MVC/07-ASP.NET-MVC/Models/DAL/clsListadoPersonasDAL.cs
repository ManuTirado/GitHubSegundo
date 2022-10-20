namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsListadoPersonasDAL
    {
        public static List<clsPersona> obtenerListadoCompletoPersonas()
        {
            List<clsPersona> listadoCompletoPersonas = new List<clsPersona>();
            listadoCompletoPersonas.Add(new clsPersona("Álvaro", "Castro"));
            listadoCompletoPersonas.Add(new clsPersona("Francisco Manuel", "Lobato"));
            listadoCompletoPersonas.Add(new clsPersona("Diego", "Hurtado"));
            listadoCompletoPersonas.Add(new clsPersona("Javier", "Muñoa"));
            listadoCompletoPersonas.Add(new clsPersona("Jose Manuel", "Estrada"));
            return listadoCompletoPersonas;
        }
    }
}
