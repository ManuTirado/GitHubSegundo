using _10_Ejer.Models.ViewModels.Utilidades;
using Entidades;
using System.Collections.ObjectModel;

namespace _10_Ejer.Models.ViewModels
{
    public class clsListadoYpersonaVM : clsVMBase
    {
        #region Atributos
        private ObservableCollection<clsPersona> listadoPersonasCompleto;
        private ObservableCollection<clsPersona> listadoPersonasMostrado;
        private clsPersona personaSeleccionada;
        private string entryBuscarPersona;
        private DelegateCommand eliminarPersonaCommand; //Comando Elminar Persona
        private DelegateCommand buscarPersonaCommand; //Comando Buscar Persona
        #endregion

        #region Propiedades
        public ObservableCollection<clsPersona> ListadoPersonasMostrado { get { return listadoPersonasMostrado; } }
        public clsPersona PersonaSeleccionada
        {
            get { return personaSeleccionada; }
            set
            {
                if (personaSeleccionada != value)
                {
                    personaSeleccionada = value;
                    eliminarPersonaCommand.RaiseCanExecuteChanged();
                    NotifyPropertyChanged(nameof(PersonaSeleccionada));
                }
            }
        }
        public string EntryBuscarPersona
        {
            get { return entryBuscarPersona; }
            set
            {
                if (entryBuscarPersona != value)
                {
                    entryBuscarPersona = value;
                    buscarPersonaCommand.RaiseCanExecuteChanged();
                    NotifyPropertyChanged(nameof(EntryBuscarPersona));
                }

            }
        }
        public DelegateCommand EliminarPersonaCommand
        {
            get
            {
                eliminarPersonaCommand = new DelegateCommand(EliminarPersonaCommand_execute, EliminarPersonaCommand_canExecute);
                return eliminarPersonaCommand;
            }
        }

        public DelegateCommand BuscarPersonaCommand
        {
            get
            {
                buscarPersonaCommand = new DelegateCommand(BuscarPersonaCommand_execute);
                return buscarPersonaCommand;
            }
        }

        #endregion

        #region Constructores
        public clsListadoYpersonaVM()
        {
            listadoPersonasCompleto = new ObservableCollection<clsPersona>(DAL.clsListadosPersonas.obtenerListadoCompleto());
            listadoPersonasMostrado = new ObservableCollection<clsPersona>(listadoPersonasCompleto);
        }
        #endregion

        #region Comandos
        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private bool EliminarPersonaCommand_canExecute()
        {
            bool hayPersonaSeleccionada = false;

            if (personaSeleccionada != null)
            {
                hayPersonaSeleccionada = true;
            }
            return hayPersonaSeleccionada;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <exception cref="NotImplementedException"></exception>
        private async void EliminarPersonaCommand_execute()
        {
            bool answer = await Application.Current.MainPage.DisplayAlert("¿Eliminar persona?", "Una vez eliminada no podrá ser recuperada", "Si", "No");
            if (answer)
            {
                listadoPersonasCompleto.Remove(PersonaSeleccionada);
                listadoPersonasMostrado.Remove(PersonaSeleccionada);
                personaSeleccionada = null;
                NotifyPropertyChanged(nameof(ListadoPersonasMostrado));
                NotifyPropertyChanged(nameof(PersonaSeleccionada));
                eliminarPersonaCommand.RaiseCanExecuteChanged();
            }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private bool BuscarPersonaCommand_canExecute()
        {
            bool hayTextoEscrito = false;

            if (!string.IsNullOrEmpty(entryBuscarPersona))
            {
                hayTextoEscrito = true;
            }
            return hayTextoEscrito;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <exception cref="NotImplementedException"></exception>
        private void BuscarPersonaCommand_execute()
        {
            if (string.IsNullOrEmpty(entryBuscarPersona))
            {
                listadoPersonasMostrado = listadoPersonasCompleto;
            }
            else
            {
                listadoPersonasMostrado.Clear();
                foreach (clsPersona persona in listadoPersonasCompleto)
                {
                    if (persona.Nombre.ToLowerInvariant().StartsWith(entryBuscarPersona.ToLowerInvariant()) || persona.Apellido.ToLowerInvariant().StartsWith(entryBuscarPersona.ToLowerInvariant()))
                    {
                        listadoPersonasMostrado.Add(persona);
                    }
                }
            }
            NotifyPropertyChanged(nameof(ListadoPersonasMostrado));
            NotifyPropertyChanged(nameof(PersonaSeleccionada));
        }
        #endregion  
    }
}
