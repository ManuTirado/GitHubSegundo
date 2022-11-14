using Entidades;
using MandalorianoMAUI.Models.VM.Utilidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MandalorianoMAUI.Models.VM
{
    public class clsListadoYMisionVM : clsVMBase
    {
        #region Atributos
        private List<clsMision> listadoMisionesCompleto;
        private clsMision misionSeleccionada;
        private DelegateCommand mostrarDetallesCommand; //Comando
        private bool datosMisionEsVisible;
        #endregion

        #region Propidedades
        public List<clsMision> ListadoMisionesCompleto
        {
            get
            {
                return listadoMisionesCompleto;
            }
        }
        public clsMision MisionSeleccionada
        {
            get
            {
                return misionSeleccionada;
            }
            set
            {
                if (misionSeleccionada != value)
                {
                    misionSeleccionada = value;
                    mostrarDetallesCommand.RaiseCanExecuteChanged();
                    datosMisionEsVisible = false;
                    NotifyPropertyChanged(nameof(DatosMisionEsVisible));
                }
            }
        }
        public DelegateCommand MostrarDetallesCommand
        {
            get
            {
                mostrarDetallesCommand = new DelegateCommand(mostrarDetallesCommand_execute, MostrarDetallesCommand_canExecute);
                return mostrarDetallesCommand;
            }
        }
        public bool DatosMisionEsVisible { get { return datosMisionEsVisible; } }
        #endregion

        #region Constructores
        public clsListadoYMisionVM()
        {
        }

        #endregion

        #region Comandos
        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private bool MostrarDetallesCommand_canExecute()
        {
            bool btnDetallesVisible = false;

            if (misionSeleccionada != null)
            {
                btnDetallesVisible = true;
            }
            return btnDetallesVisible;
        }

        /// <summary>
        /// Método que muestra los detalles de la misión seleccionada haciendo visible
        /// el texto mediante el bool datosMisionEsVisible
        /// </summary>
        /// <exception cref="NotImplementedException"></exception>
        private void mostrarDetallesCommand_execute()
        {
            datosMisionEsVisible = true;
            NotifyPropertyChanged(nameof(DatosMisionEsVisible));
            NotifyPropertyChanged(nameof(MisionSeleccionada));
        }
        #endregion
    }
}
