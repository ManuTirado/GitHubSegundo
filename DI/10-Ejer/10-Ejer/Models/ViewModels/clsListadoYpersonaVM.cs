using _10_Ejer.Models.ViewModels.Utilidades;
using Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _10_Ejer.Models.ViewModels
{
    public class clsListadoYpersonaVM : clsVMBase
    {
        #region Atributos
        private List<clsPersona> listadoPersonas;
        private clsPersona personaSeleccionada;
        private DelegateCommand eliminarPersonaCommand; //Comando
        #endregion

        #region Propiedades
        public List<clsPersona> ListadoPersonas { get { return listadoPersonas; } }
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
        public DelegateCommand EliminarPersonaCommand
        {
            get
            {
                eliminarPersonaCommand = new DelegateCommand(EliminarPersonaCommand_execute, EliminarPersonaCommand_canExecute);
                return eliminarPersonaCommand;
            }
        }

        #endregion

        #region Constructores
        public clsListadoYpersonaVM()
        {
            listadoPersonas = DAL.clsListadosPersonas.obtenerListadoCompleto();
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
        private void EliminarPersonaCommand_execute()
        {
            listadoPersonas.Remove(PersonaSeleccionada);
            personaSeleccionada = null;
            NotifyPropertyChanged(nameof(ListadoPersonas));
            NotifyPropertyChanged(nameof(PersonaSeleccionada));
        }
        #endregion
    }
}
