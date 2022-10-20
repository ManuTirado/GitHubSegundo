namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsListadosDepartamentos
    {
        public static List<clsDepartamento> listadoCompletoDepartamentos()
        {
            List<clsDepartamento> departamentos = new List<clsDepartamento>();
            departamentos.Add(new clsDepartamento());
            return departamentos;
        }
    }
}
