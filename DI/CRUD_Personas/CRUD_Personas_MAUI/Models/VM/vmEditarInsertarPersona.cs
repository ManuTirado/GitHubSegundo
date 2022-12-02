using CRUD_Personas_BL.Listados;
using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using Microsoft.Data.SqlClient;

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
        public clsPersona PersonaSeleccionada
        {
            get { return personaSeleccionada; }
            set
            {
                personaSeleccionada = value;
                departamentoSeleccionado = listaDepartmentos.Find(x => x.ID == personaSeleccionada.IDDepartamento);
                NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
                NotifyPropertyChanged(nameof(PersonaSeleccionada));
                //guardarPersona.RaiseCanExecuteChanged();
            }
        }
        public List<clsDepartamento> ListaDepartmentos { get { return listaDepartmentos; } set { listaDepartmentos = value; NotifyPropertyChanged(nameof(ListaDepartmentos)); } }
        public clsDepartamento DepartamentoSeleccionado
        {
            get { return departamentoSeleccionado; }
            set
            {
                departamentoSeleccionado = value;
                personaSeleccionada.IDDepartamento = departamentoSeleccionado.ID;
                NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
                NotifyPropertyChanged(nameof(PersonaSeleccionada));
            }
        }
        public DelegateCommand GuardarPersona { get { guardarPersona = new DelegateCommand(GuardarPersonaCommand_execute, GuardarPersonaCommand_canExecute); ; return guardarPersona; } }
        #endregion

        #region Constructores
        public vmEditarInsertarPersona()
        {
            listaDepartmentos = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
            personaSeleccionada = new clsPersona();
        }
        #endregion

        #region Comandos
        private bool GuardarPersonaCommand_canExecute()
        {
            return true;
            /*
            if (personaSeleccionada.Nombre != null)
            {
                return true;
            }
            else
            {
                return false;
            }
            */
        }
        private async void GuardarPersonaCommand_execute()
        {
            if (personaSeleccionada.ID != 0)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.EditarPersonaBL(personaSeleccionada.ID, personaSeleccionada);
                    await Application.Current.MainPage.DisplayAlert("Correcto", "Persona editada correctamente", "OK");
                    await Shell.Current.GoToAsync("..");
                }
                catch (SqlException e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar editar la persona", "Los campos marcados con * son obligatorios", "OK");
                }
            }
            else
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.InsertarPersonaBL(personaSeleccionada);
                    await Application.Current.MainPage.DisplayAlert("Correcto", "Persona insertada correctamente", "OK");
                    await Shell.Current.GoToAsync("..");
                }
                catch (SqlException e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar añadir la persona", "Los campos marcados con * son obligatorios", "OK");
                }
            }
        }
            #endregion
        }
    }
