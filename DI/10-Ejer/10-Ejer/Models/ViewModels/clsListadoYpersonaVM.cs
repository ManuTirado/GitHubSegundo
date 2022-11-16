using _10_Ejer.Models.ViewModels.Utilidades;
using Entidades;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _10_Ejer.Models.ViewModels
{
    public class clsListadoYpersonaVM : clsVMBase
    {
        #region Atributos
        private ObservableCollection<clsPersona> listadoPersonas;
        private clsPersona personaSeleccionada;
        private string entryBuscarPersona;
        private DelegateCommand eliminarPersonaCommand; //Comando Elminar Persona
        private DelegateCommand buscarPersonaCommand; //Comando Buscar Persona
        #endregion

        #region Propiedades
        public ObservableCollection<clsPersona> ListadoPersonas { get { return listadoPersonas; } }
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
                buscarPersonaCommand = new DelegateCommand(BuscarPersonaCommand_execute, BuscarPersonaCommand_canExecute);
                return buscarPersonaCommand;
            }
        }

        #endregion

        #region Constructores
        public clsListadoYpersonaVM()
        {
            listadoPersonas = new ObservableCollection<clsPersona>(DAL.clsListadosPersonas.obtenerListadoCompleto());
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
                listadoPersonas.Remove(PersonaSeleccionada);
                personaSeleccionada = null;
                NotifyPropertyChanged(nameof(ListadoPersonas));
                NotifyPropertyChanged(nameof(PersonaSeleccionada));
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
            listadoPersonas = new ObservableCollection<clsPersona>(listadoPersonas.OrderBy(m => m.Nombre));
            NotifyPropertyChanged(nameof(ListadoPersonas));
        }
        #endregion  
    }
}
