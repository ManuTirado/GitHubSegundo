using CRUD_Personas_Entidades;

namespace CRUD_Personas_MAUI.Models
{
    public class clsPersonaNombreDepartamento : clsPersona
    {
        public string NombreDepartamento { get; set; }

        public clsPersonaNombreDepartamento ()
        {

        }
        public clsPersonaNombreDepartamento(clsPersona persona)
        {
            this.ID = persona.ID;
            this.Nombre = persona.Nombre;
            this.Apellidos = persona.Apellidos;
            this.Telefono = persona.Telefono;
            this.Direccion = persona.Direccion;
            this.Foto = persona.Foto;
            this.FechaNacimiento = persona.FechaNacimiento;
            this.IDDepartamento = persona.IDDepartamento;
        }
    }
}
