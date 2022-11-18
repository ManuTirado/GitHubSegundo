namespace CRUD_Personas_Entidades
{
    public class clsPersona
    {
        #region Atributos
        public int ID { get; set; }

        public string Nombre { get; set; }

        public string Apellidos { get; set; }

        public string Telefono { get; set; }
        public string Direccion { get; set; }
        public string Foto { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public int IDDepartamento { get; set; }
        #endregion

        #region Constructores
        public clsPersona(int id, string nombre, string apellido, string telefono, string direccion, string foto, DateTime fechaNacimiento, int iDDepartamento)
        {
            ID = id;
            Nombre = nombre;
            Apellidos = apellido;
            Telefono = telefono;
            Direccion = direccion;
            Foto = foto;
            FechaNacimiento = fechaNacimiento;
            IDDepartamento = iDDepartamento;
        }
        public clsPersona()
        {

        }
        #endregion
    }
}