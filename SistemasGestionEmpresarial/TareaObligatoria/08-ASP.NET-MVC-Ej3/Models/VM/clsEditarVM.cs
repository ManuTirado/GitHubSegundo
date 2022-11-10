using Entidades;

namespace _08_ASP.NET_MVC_Ej3.Models.VM
{
    public class clsEditarVM
    {
        public clsPersona Persona { get; set; }
        public List<clsDepartamento> ListadoDepartamento { get; set; }

        public clsEditarVM (clsPersona persona, List<clsDepartamento> listadoDepartamento)
        {
            this.Persona = persona;
            this.ListadoDepartamento = listadoDepartamento;
        }

        public clsEditarVM ()
        {

        }
    }
}
