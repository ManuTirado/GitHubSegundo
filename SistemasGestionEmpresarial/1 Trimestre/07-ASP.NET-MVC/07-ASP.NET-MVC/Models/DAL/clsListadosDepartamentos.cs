namespace _07_ASP.NET_MVC.Models.DAL
{
    public static class clsListadosDepartamentos
    {
        public static List<clsDepartamento> listadoCompletoDepartamentos()
        {
            List<clsDepartamento> departamentos = new List<clsDepartamento>();
            departamentos.Add(new clsDepartamento(1, "Contabilidad"));
            departamentos.Add(new clsDepartamento(2, "Produccion"));
            departamentos.Add(new clsDepartamento(3, "Marketing"));
            departamentos.Add(new clsDepartamento(4, "Recursos Humanos"));
            return departamentos;
        }
    }
}
