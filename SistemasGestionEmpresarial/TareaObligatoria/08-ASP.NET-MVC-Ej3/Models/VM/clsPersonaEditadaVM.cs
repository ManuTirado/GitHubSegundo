using Entidades;

namespace _08_ASP.NET_MVC_Ej3.Models.VM
{
    public class clsPersonaEditadaVM
    {
        public clsPersona Persona { get; set; }
        public string NombreDepartamento { get; set; }

        public clsPersonaEditadaVM (clsPersona persona, string nombreDepartamento)
        {
            Persona = persona;
            NombreDepartamento = nombreDepartamento;
        }

        public clsPersonaEditadaVM ()
        {

        }
    }
}
