using System.ComponentModel.DataAnnotations;

namespace U10_Ej2.Models.Entidades
{
    public class clsPersona
    {
        public int ID { get; set; }

        public string Nombre { get; set; }

        public string Apellidos { get; set; }

        public string Telefono { get; set; }
        public string Direccion { get; set; }
        public string Foto { get; set; }
        public DateTime FechaNacimiento { get; set; }
        public int IDDepartamento { get; set; }



        public clsPersona (int id, string nombre, string apellido, string telefono, string direccion, string foto, DateTime fechaNacimiento, int iDDepartamento )
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
        public clsPersona ()
        {
            
        }
    }
}