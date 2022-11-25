using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using CRUD_Personas_BL.Listados;
using System.Collections.ObjectModel;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmListadoPersonas : clsVMBase
    {
        #region Atributos
        private List<clsPersona> listaPersonasBackup;
        private ObservableCollection<clsPersonaNombreDepartamento> listaPersonas;
        private List<clsDepartamento> listaDepartamentos;
        private clsPersonaNombreDepartamento personaSeleccionada;
        private string busquedaUsuario;

        DelegateCommand eliminarPersona;
        DelegateCommand buscarPersona;
        DelegateCommand editarPersona;
        DelegateCommand anadirPersona;
        #endregion

        #region Propiedades
        public ObservableCollection<clsPersonaNombreDepartamento> ListaPersonas
        {
            get { return listaPersonas; }
        }
        public clsPersonaNombreDepartamento PersonaSeleccionada
        {
            get { return personaSeleccionada; }
            set
            {
                if (personaSeleccionada != value)
                {
                    personaSeleccionada = value;
                    // eliminarPersona.RaiseCanExecuteChanged();
                    // editarPersona.RaiseCanExecuteChanged();
                    NotifyPropertyChanged(nameof(PersonaSeleccionada));
                }
            }
        }
        public string BusquedaUsuario
        {
            get { return busquedaUsuario; }
            set
            {
                if (busquedaUsuario != value)
                {
                    busquedaUsuario = value;
                    // buscarPersona.RaiseCanExecuteChanged();
                    NotifyPropertyChanged(nameof(BusquedaUsuario));
                }
            }
        }
        public DelegateCommand EliminarPersonaCommand
        {
            get
            {
                return eliminarPersona;
            }
        }
        public DelegateCommand BuscarPersonaCommand
        {
            get
            {
                return buscarPersona;
            }
        }
        public DelegateCommand EditarPersonaCommand
        {
            get
            {
                return editarPersona;
            }
        }
        public DelegateCommand AnadirPersonaCommand
        {
            get
            {
                return anadirPersona;
            }
        }
        #endregion

        #region Constructores
        public vmListadoPersonas ()
        {
            listaPersonasBackup = clsListadosPersonasBL.ListadoCompletoPersonasBL();
            listaDepartamentos = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
            foreach (var persona in listaPersonasBackup)
            {
                clsPersonaNombreDepartamento personaNombreDepartamento = (clsPersonaNombreDepartamento) persona;
                personaNombreDepartamento.NombreDepartamento = listaDepartamentos.Find(x => x.ID == persona.IDDepartamento).Nombre;
                listaPersonas.Add(personaNombreDepartamento);
            }
            
            eliminarPersona = new DelegateCommand(EliminarPersonaCommand_execute, EliminarPersonaCommand_canExecute);
            buscarPersona = new DelegateCommand(BuscarPersonaCommand_execute, BuscarPersonaCommand_canExecute); 
            editarPersona = new DelegateCommand(EditarPersonaCommand_execute, EditarPersonaCommand_canExecute); 
            anadirPersona = new DelegateCommand(AnadirPersonaCommand_execute, AnadirPersonaCommand_canExecute); 
        }
        #endregion

        #region Comandos
        #endregion
    }
}
