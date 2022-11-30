using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using CRUD_Personas_BL.Listados;
using System.Collections.ObjectModel;
using CRUD_Personas_MAUI.Paginas;

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
                    eliminarPersona.RaiseCanExecuteChanged();
                    editarPersona.RaiseCanExecuteChanged();
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
                    NotifyPropertyChanged(nameof(BusquedaUsuario));
                }
            }
        }
        public DelegateCommand EliminarPersonaCommand
        {
            get
            {
                eliminarPersona = new DelegateCommand(EliminarPersonaCommand_executeAsync, EliminarPersonaCommand_canExecute);
                return eliminarPersona;
            }
        }

        public DelegateCommand BuscarPersonaCommand
        {
            get
            {
                buscarPersona = new DelegateCommand(BuscarPersonaCommand_execute, BuscarPersonaCommand_canExecute);
                return buscarPersona;
            }
        }
        public DelegateCommand EditarPersonaCommand
        {
            get
            {
                editarPersona = new DelegateCommand(EditarPersonaCommand_execute, EditarPersonaCommand_canExecute);
                return editarPersona;
            }
        }
        public DelegateCommand AnadirPersonaCommand
        {
            get
            {
                anadirPersona = new DelegateCommand(AnadirPersonaCommand_execute);  // Siempre se puede pulsar
                return anadirPersona;
            }
        }
        #endregion

        #region Constructores
        public vmListadoPersonas()
        {
            listaPersonasBackup = clsListadosPersonasBL.ListadoCompletoPersonasBL();
            listaDepartamentos = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
            listaPersonas = obtenerListaConNombreDepartamento(listaPersonasBackup);
        }
        #endregion

        #region Comandos
        private bool EliminarPersonaCommand_canExecute()
        {
            bool hayPersonaSeleccionada = false;

            if (personaSeleccionada != null)
            {
                hayPersonaSeleccionada = true;
            }
            return hayPersonaSeleccionada;
        }
        private async void EliminarPersonaCommand_executeAsync()
        {
            bool answer = await Application.Current.MainPage.DisplayAlert("¿Eliminar persona?", "Una vez eliminada no podrá ser recuperada", "Si", "No");
            if (answer)
            {
                listaPersonasBackup.Remove(listaPersonasBackup.Find(x => x.ID == personaSeleccionada.ID));
                listaPersonas.Remove(PersonaSeleccionada);
                personaSeleccionada = null;
                NotifyPropertyChanged(nameof(ListaPersonas));
                NotifyPropertyChanged(nameof(PersonaSeleccionada));
                EliminarPersonaCommand.RaiseCanExecuteChanged();
            }
        }

        private bool BuscarPersonaCommand_canExecute()
        {
            return true;
        }
        private void BuscarPersonaCommand_execute()
        {
            if (string.IsNullOrEmpty(busquedaUsuario))
            {
                listaPersonas = new ObservableCollection<clsPersonaNombreDepartamento>(obtenerListaConNombreDepartamento(listaPersonasBackup));
            }
            else
            {
                List<clsPersona> listadoPersonasMostrado = new List<clsPersona>();
                listaPersonas.Clear();
                foreach (clsPersona persona in listaPersonasBackup)
                {
                    if (persona.Nombre.ToLower().StartsWith(busquedaUsuario.ToLowerInvariant()) || persona.Apellidos.ToLower().StartsWith(busquedaUsuario.ToLowerInvariant()))
                    {
                        listadoPersonasMostrado.Add(persona);
                    }
                }
                listaPersonas = new ObservableCollection<clsPersonaNombreDepartamento>(obtenerListaConNombreDepartamento(listadoPersonasMostrado));
            }
            NotifyPropertyChanged(nameof(ListaPersonas));
            NotifyPropertyChanged(nameof(PersonaSeleccionada));
        }

        private bool EditarPersonaCommand_canExecute()
        {
            bool hayPersonaSeleccionada = false;

            if (personaSeleccionada != null)
            {
                hayPersonaSeleccionada = true;
            }
            return hayPersonaSeleccionada;
        }
        private async void EditarPersonaCommand_execute()
        {
            clsPersona personaPasada = new clsPersona(personaSeleccionada.ID, personaSeleccionada.Nombre, personaSeleccionada.Apellidos, personaSeleccionada.Telefono, personaSeleccionada.Direccion, personaSeleccionada.Foto, personaSeleccionada.FechaNacimiento, personaSeleccionada.IDDepartamento);
            var miDiccionario = new Dictionary<string, object>
            {
                { "PersonaPasada", personaPasada }
            };
            await Shell.Current.GoToAsync("DetallesPersona", miDiccionario);
        }

        private async void AnadirPersonaCommand_execute()
        {
            await Shell.Current.GoToAsync("DetallesPersona");
        }
        #endregion

        #region Métodos
        private ObservableCollection<clsPersonaNombreDepartamento> obtenerListaConNombreDepartamento(List<clsPersona> listaBackup)
        {
            ObservableCollection<clsPersonaNombreDepartamento> listaFinal = new ObservableCollection<clsPersonaNombreDepartamento>();
            foreach (var persona in listaBackup)
            {
                clsPersonaNombreDepartamento personaNombreDepartamento = new clsPersonaNombreDepartamento(persona);
                personaNombreDepartamento.NombreDepartamento = listaDepartamentos.Find(x => x.ID == persona.IDDepartamento).Nombre;
                listaFinal.Add(personaNombreDepartamento);
            }
            return listaFinal;
        }
        #endregion
    }
}
