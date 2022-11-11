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
        private DelegateCommand mostrarDetallesCommand;
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
                misionSeleccionada = value;
            }
        }
        public DelegateCommand MostrarDetallesCommand
        {
            get
            {
                mostrarDetallesCommand = new DelegateCommand(mostrarDetallesCommand_execute, mostrarDetallesCommand_canExecute);
                return mostrarDetallesCommand;
            }
        }
        public bool DatosMisionEsVisible 
        {
            get
            {
                return datosMisionEsVisible;
            }
        }
        #endregion

        #region Constructores

        #endregion

        #region Comandos
        /// <summary>
        /// 
        /// </summary>
        /// <returns></returns>
        /// <exception cref="NotImplementedException"></exception>
        private bool mostrarDetallesCommand_canExecute()
        {
            return true;
        }

        /// <summary>
        /// Método que muestra los detalles de la misión seleccionada haciendo visible
        /// el texto mediante el bool datosMisionEsVisible
        /// </summary>
        /// <exception cref="NotImplementedException"></exception>
        private void mostrarDetallesCommand_execute()
        {
            datosMisionEsVisible = true;
        }
        #endregion
    }
}
