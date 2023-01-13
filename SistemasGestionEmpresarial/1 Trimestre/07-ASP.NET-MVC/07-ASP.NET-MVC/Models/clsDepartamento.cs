namespace _07_ASP.NET_MVC.Models
{
    public class clsDepartamento
    {
        private int idDepartamento;
        private string nombreDepartamento;

        public clsDepartamento(int idDepartamento, string nombreDepartamento)
        {
            this.idDepartamento = idDepartamento;
            this.nombreDepartamento = nombreDepartamento;
        }

        public int IdDepartamento { get { return idDepartamento; } set { idDepartamento = value; } }
        public string NombreDepartamento { get { return nombreDepartamento; } set { nombreDepartamento = value; } }
    }
}
