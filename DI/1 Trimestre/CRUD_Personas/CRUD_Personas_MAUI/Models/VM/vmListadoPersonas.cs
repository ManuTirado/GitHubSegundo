using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using CRUD_Personas_BL.Listados;
using System.Collections.ObjectModel;
using Microsoft.Data.SqlClient;

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
        DelegateCommand actualizarListaCommand;
        private bool isRefreshing;
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
        public DelegateCommand ActualizarListaCommand
        {
            get
            {
                return actualizarListaCommand;
            }
        }

        public bool IsRefreshing { get { return isRefreshing; } set { isRefreshing = value; NotifyPropertyChanged(nameof(IsRefreshing)); NotifyPropertyChanged(nameof(IsNotRefreshing)); } }
        #endregion

        #region Constructores
        public vmListadoPersonas()
        {
            Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
            hiloActualizar.Start();
            eliminarPersona = new DelegateCommand(EliminarPersonaCommand_execute, EliminarPersonaCommand_canExecute);
            buscarPersona = new DelegateCommand(BuscarPersonaCommand_execute, BuscarPersonaCommand_canExecute);
            editarPersona = new DelegateCommand(EditarPersonaCommand_execute, EditarPersonaCommand_canExecute);
            anadirPersona = new DelegateCommand(AnadirPersonaCommand_execute);  // Siempre se puede pulsar
            actualizarListaCommand = new DelegateCommand(ActualizarListaCommand_execute);
        }
        #endregion

        #region Comandos
        /// <summary>
        /// Compruebo que haya una persona seleccionada, en caso afirmativo,
        /// se puede ejecutar el comando.
        /// </summary>
        /// <returns>true si hay una persona seleccionada</returns>
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
        /// Pido una confirmación al usuario, en caso de que confirme,
        /// realizo la eliminación de la persona seleccionada en la BBDD y 
        /// actualizo las listas que usa el VM y establezco la persona seleccionada a null.
        /// </summary>
        private async void EliminarPersonaCommand_execute()
        {
            bool answer = await Application.Current.MainPage.DisplayAlert("¿Eliminar persona?", "Una vez eliminada no podrá ser recuperada", "Si", "No");
            if (answer)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraPersonasBL.BorrarPersonaBL(personaSeleccionada.ID);
                    listaPersonasBackup.Remove(listaPersonasBackup.Find(x => x.ID == personaSeleccionada.ID));
                    listaPersonas.Remove(PersonaSeleccionada);
                    personaSeleccionada = null;
                    NotifyPropertyChanged(nameof(ListaPersonas));
                    NotifyPropertyChanged(nameof(PersonaSeleccionada));
                    EliminarPersonaCommand.RaiseCanExecuteChanged();
                }
                catch (Exception e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar borrar la persona", "'" + e.Message + "' XD'nt", "OK");
                }
            }
        }

        private bool BuscarPersonaCommand_canExecute()
        {
            return true;
        }
        /// <summary>
        /// Actualizo la lista de personas visible a los elementos de la lista backup que coincidan con
        /// los parámetros de búsqueda.
        /// </summary>
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

        /// <summary>
        /// Compruebo que haya una persona seleccionada, en caso afirmativo
        /// se puede ejecutar el comando.
        /// </summary>
        /// <returns>true si hay una persona seleccionada</returns>
        private bool EditarPersonaCommand_canExecute()
        {
            bool hayPersonaSeleccionada = false;

            if (personaSeleccionada != null)
            {
                hayPersonaSeleccionada = true;
            }
            return hayPersonaSeleccionada;
        }
        /// <summary>
        /// Navego a la página de DetallesPersona pasándole la persona seleccionada en la navegación.
        /// </summary>
        private async void EditarPersonaCommand_execute()
        {
            clsPersona personaPasada = new clsPersona(personaSeleccionada.ID, personaSeleccionada.Nombre, personaSeleccionada.Apellidos, personaSeleccionada.Telefono, personaSeleccionada.Direccion, personaSeleccionada.Foto, personaSeleccionada.FechaNacimiento, personaSeleccionada.IDDepartamento);
            var miDiccionario = new Dictionary<string, object>
            {
                { "PersonaPasada", personaPasada }
            };
            await Shell.Current.GoToAsync("DetallesPersona", miDiccionario);
        }

        /// <summary>
        /// Navego a la página DetallesPersona sin pasarle nada.
        /// </summary>
        private async void AnadirPersonaCommand_execute()
        {
            await Shell.Current.GoToAsync("DetallesPersona");
        }

        /// <summary>
        /// Lanza un nuevo hilo que se encarga de actualizar la lista de personas.
        /// </summary>
        private void ActualizarListaCommand_execute()
        {
            Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
            hiloActualizar.Start();
        }
        #endregion

        /// <summary>
        /// Método que actualiza la lista de personas backup obteniéndola de la BBDD.
        /// También restablece la busqueda y la lista mostrada.
        /// </summary>
        public async void actualizarDatos()
        {

            IsRefreshing = true;
            NotifyPropertyChanged(nameof(IsRefreshing));
            NotifyPropertyChanged(nameof(IsNotRefreshing));
            busquedaUsuario = "";
            NotifyPropertyChanged(nameof(BusquedaUsuario));
            try
            {
                Thread.Sleep(1000);
                listaPersonasBackup = clsListadosPersonasBL.ListadoCompletoPersonasBL();
                listaDepartamentos = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
            }
            catch (Exception e)
            {
                bool volverAintentar = await Application.Current.MainPage.DisplayAlert("Error al cargar las personas", "'" + e.Message + "' XD'nt", "Recargar", "Salir");
                if (volverAintentar)
                {
                    Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
                    hiloActualizar.Start();
                }
            }
            finally
            {
                listaPersonas = obtenerListaConNombreDepartamento(listaPersonasBackup);
                NotifyPropertyChanged(nameof(ListaPersonas));
                IsRefreshing = false;
                NotifyPropertyChanged(nameof(IsRefreshing));
                NotifyPropertyChanged(nameof(IsNotRefreshing));
            }
        }

        #region Métodos
        /// <summary>
        /// Función que devuelve una ObservableCollection de clsPersonaNombreDepartamento a partir de un List de clsPersona.
        /// Para ello, según el id de departamento de clsPersona compruebo el departamento que le corresponde.
        /// </summary>
        /// <param name="listaBackup"></param>
        /// <returns></returns>
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

        public bool IsNotRefreshing { get { return !isRefreshing; } }
        #endregion
    }
}
