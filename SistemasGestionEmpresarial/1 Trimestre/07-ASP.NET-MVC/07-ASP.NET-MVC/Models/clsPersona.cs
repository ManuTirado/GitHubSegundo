namespace _07_ASP.NET_MVC.Models
{
    public class clsPersona
    {
        private int id;
        private string nombre;
        private string apellido;
        private int departamento;

        public clsPersona()
        {
        }
        public clsPersona(string Nombre, string Apellido)
        {
            nombre = Nombre;
            apellido = Apellido;
        }
        public clsPersona(string Nombre, string Apellido, int Departamento)
        {
            nombre = Nombre;
            apellido = Apellido;
            departamento = Departamento;
        }
        public clsPersona(int Id, string Nombre, string Apellido, int Departamento)
        {
            id = Id;
            nombre = Nombre;
            apellido = Apellido;
            departamento = Departamento;
        }
        public string Nombre { get { return nombre; } set { nombre = value; } }
        public string Apellido { get { return apellido; } set { apellido = value; } }
        public int Departamento { get { return departamento; } set { departamento = value; } }
        public int Id { get { return id; } set { departamento = id; } }

    }
}