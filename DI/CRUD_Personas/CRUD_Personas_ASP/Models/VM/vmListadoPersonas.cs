using CRUD_Personas_Entidades;

namespace CRUD_Personas_ASP.Models.VM
{
    public class vmListadoPersonas
    {
        #region Atributos
        private List<clsDepartamento> listaDepartamentos;
        private List<clsPersonaNombreDepartmento> listaPersonas;
        private clsPersonaNombreDepartmento personaSeleccionada;
        #endregion

        #region Propiedades
        public List<clsPersonaNombreDepartmento> ListaPersonas { get { return listaPersonas; } }
        public clsPersonaNombreDepartmento PersonaSeleccionada { get { return personaSeleccionada; } set { personaSeleccionada = value; } }
        #endregion

        #region Constructores
        public vmListadoPersonas()
        {

        }
        public vmListadoPersonas(List<clsPersonaNombreDepartmento> listaPersonas, clsPersonaNombreDepartmento personaSeleccionada)
        {
            this.listaPersonas = listaPersonas;
            this.personaSeleccionada = personaSeleccionada;

        }

        #endregion
    }
}
