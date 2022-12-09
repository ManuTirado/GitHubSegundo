using CRUD_Personas_Entidades;

namespace CRUD_Personas_ASP.Models
{
    public class clsPersonaNombreDepartmento
    {
        #region Propiedades
        public clsPersona persona { get; set; }
        public String nombreDepartamento { get; set; }
        #endregion

        #region Constructores
        public clsPersonaNombreDepartmento() { }
        public clsPersonaNombreDepartmento(clsPersona persona)
        {
            this.persona = persona;
        }
        
        #endregion
    }
}
