using System.ComponentModel.DataAnnotations;

namespace CRUD_Personas_Entidades
{
    public class clsPersona
    {
        #region Atributos
        public int id { get; set; }

        [Required]
        public string nombre { get; set; }

        [Required]
        public string apellidos { get; set; }

        public string telefono { get; set; }
        public string direccion { get; set; }
        public string foto { get; set; }

        [Required]
        public DateTime fechaNacimiento { get; set; }

        [Required]
        public int idDepartamento { get; set; }
        #endregion

        #region Constructores
        public clsPersona(int id, string nombre, string apellido, string telefono, string direccion, string foto, DateTime fechaNacimiento, int iDDepartamento)
        {
            this.id = id;
            this.nombre = nombre;
            this.apellidos = apellido;
            this.telefono = telefono;
            this.direccion = direccion;
            this.foto = foto;
            this.fechaNacimiento = fechaNacimiento;
            this.idDepartamento = iDDepartamento;
        }
        public clsPersona()
        {

        }
        #endregion
    }
}