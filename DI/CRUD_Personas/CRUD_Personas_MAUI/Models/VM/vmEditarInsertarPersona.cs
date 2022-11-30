using CRUD_Personas_BL.Listados;
using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;

namespace CRUD_Personas_MAUI.Models.VM
{
    [QueryProperty(nameof(PersonaSeleccionada), "PersonaPasada")]
    public class vmEditarInsertarPersona : clsVMBase
    {
        #region Atributos
        clsPersona personaSeleccionada;
        List<clsDepartamento> listaDepartmentos;
        clsDepartamento departamentoSeleccionado;
        DelegateCommand guardarPersona; // Diferenciar entre inserción o actualización
        #endregion

        #region Propiedades
        public clsPersona PersonaSeleccionada { get { return personaSeleccionada; } set { personaSeleccionada = value; NotifyPropertyChanged(nameof(PersonaSeleccionada)); } }
        public List<clsDepartamento> ListaDepartmentos { get { return listaDepartmentos; } set { listaDepartmentos = value; NotifyPropertyChanged(nameof(ListaDepartmentos)); } }
        public clsDepartamento DepartamentoSeleccionado
        {
            get { return departamentoSeleccionado; }
            set
            {
                departamentoSeleccionado = value;
                personaSeleccionada.IDDepartamento = departamentoSeleccionado.ID;
                NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
            }
        }
        public DelegateCommand GuardarPersona { get { guardarPersona = new DelegateCommand(GuardarPersonaCommand_execute, GuardarPersonaCommand_canExecute); ; return guardarPersona; } }
        #endregion

        #region Constructores
        public vmEditarInsertarPersona()
        {
            listaDepartmentos = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
        }
        #endregion

        #region Comandos
        private bool GuardarPersonaCommand_canExecute()
        {
            return true;
        }
        private async void GuardarPersonaCommand_execute()
        {
            bool answer = await Application.Current.MainPage.DisplayAlert("¿Guardar persona?", "Hay que hacerlo :/", "Si", "No");
        }
        #endregion
    }
}
