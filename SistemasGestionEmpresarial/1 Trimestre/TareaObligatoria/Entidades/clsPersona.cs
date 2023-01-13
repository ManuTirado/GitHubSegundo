using System.ComponentModel.DataAnnotations;

namespace Entidades
{
    public class clsPersona
    {
        [Required(ErrorMessage = "Campo obligatorio")]
        public int IdPersona { get; set; }

        [Required(ErrorMessage = "Campo obligatorio")]
        public string Nombre { get; set; }

        [Required(ErrorMessage = "Campo obligatorio")]
        public string Apellido { get; set; }

        [Required(ErrorMessage = "Campo obligatorio")]
        public string Telefono { get; set; }

        public int IdDepartamento { get; set; }


        public clsPersona (int id, string nombre, string apellido, string telefono, int idDepartamento)
        {
            IdPersona = id;
            Nombre = nombre;
            Apellido = apellido;
            Telefono = telefono;
            IdDepartamento = idDepartamento;
        }
        public clsPersona ()
        {
            
        }
    }
}