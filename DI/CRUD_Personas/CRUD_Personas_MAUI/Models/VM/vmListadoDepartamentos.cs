using CRUD_Personas_BL.Listados;
using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using Microsoft.Data.SqlClient;
using System.Collections.ObjectModel;

namespace CRUD_Personas_MAUI.Models.VM
{
    public class vmListadoDepartamentos : clsVMBase
    {
        #region Atributos
        private List<clsDepartamento> listaDepartamentosBackup;
        private ObservableCollection<clsDepartamento> listaDepartamentos;
        private clsDepartamento departamentoSeleccionado;
        private string busquedaUsuario;

        DelegateCommand eliminarDepartamento;
        DelegateCommand buscarDepartamento;
        DelegateCommand editarDepartamento;
        DelegateCommand anadirDepartamento;
        DelegateCommand actualizarListaCommand;
        private bool isRefreshing;
        #endregion

        #region Propiedades
        public ObservableCollection<clsDepartamento> ListaDepartamentos
        {
            get { return listaDepartamentos; }
        }
        public clsDepartamento DepartamentoSeleccionado
        {
            get { return departamentoSeleccionado; }
            set
            {
                if (departamentoSeleccionado != value)
                {
                    departamentoSeleccionado = value;
                    eliminarDepartamento.RaiseCanExecuteChanged();
                    editarDepartamento.RaiseCanExecuteChanged();
                    NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
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
        public DelegateCommand EliminarDepartamentoCommand
        {
            get
            {
                return eliminarDepartamento;
            }
        }

        public DelegateCommand BuscarDepartamentoCommand
        {
            get
            {
                return buscarDepartamento;
            }
        }
        public DelegateCommand EditarDepartamentoCommand
        {
            get
            {
                return editarDepartamento;
            }
        }
        public DelegateCommand AnadirDepartamentoCommand
        {
            get
            {
                return anadirDepartamento;
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
        public vmListadoDepartamentos()
        {
            Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
            hiloActualizar.Start();
            eliminarDepartamento = new DelegateCommand(EliminarDepartamentoCommand_execute, EliminarDepartamentoCommand_canExecute);
            buscarDepartamento = new DelegateCommand(BuscarDepartamentoCommand_execute, BuscarDepartamentoCommand_canExecute);
            editarDepartamento = new DelegateCommand(EditarDepartamentoCommand_execute, EditarDepartamentoCommand_canExecute);
            anadirDepartamento = new DelegateCommand(AnadirDepartamentoCommand_execute);  // Siempre se puede pulsar
            actualizarListaCommand = new DelegateCommand(ActualizarListaCommand_execute);
        }
        #endregion

        #region Comandos
        private bool EliminarDepartamentoCommand_canExecute()
        {
            bool hayDepartamentoSeleccionado = false;

            if (departamentoSeleccionado != null)
            {
                hayDepartamentoSeleccionado = true;
            }
            return hayDepartamentoSeleccionado;
        }
        private async void EliminarDepartamentoCommand_execute()
        {
            bool answer = await Application.Current.MainPage.DisplayAlert("¿Eliminar departamento?", "Una vez eliminada no podrá ser recuperada", "Si", "No");
            if (answer)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.BorrarDepartamentoBL(departamentoSeleccionado.ID);
                    listaDepartamentosBackup.Remove(listaDepartamentosBackup.Find(x => x.ID == departamentoSeleccionado.ID));
                    listaDepartamentos.Remove(DepartamentoSeleccionado);
                    departamentoSeleccionado = null;
                    NotifyPropertyChanged(nameof(ListaDepartamentos));
                    NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
                    EliminarDepartamentoCommand.RaiseCanExecuteChanged();
                }
                catch (Exception e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar borrar el departamento", "Puede que haya personas en el departamento", "OK");
                }
            }
        }

        private bool BuscarDepartamentoCommand_canExecute()
        {
            return true;
        }
        private void BuscarDepartamentoCommand_execute()
        {
            if (string.IsNullOrEmpty(busquedaUsuario))
            {
                listaDepartamentos = new ObservableCollection<clsDepartamento>(listaDepartamentosBackup);
            }
            else
            {
                List<clsDepartamento> listadoDepartamentosMostrado = new List<clsDepartamento>();
                listaDepartamentos.Clear();
                foreach (clsDepartamento departamento in listaDepartamentosBackup)
                {
                    if (departamento.Nombre.ToLower().StartsWith(busquedaUsuario.ToLowerInvariant()))
                    {
                        listadoDepartamentosMostrado.Add(departamento);
                    }
                }
                listaDepartamentos = new ObservableCollection<clsDepartamento>(listadoDepartamentosMostrado);
            }
            NotifyPropertyChanged(nameof(ListaDepartamentos));
            NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
        }

        private bool EditarDepartamentoCommand_canExecute()
        {
            bool hayPersonaSeleccionada = false;

            if (departamentoSeleccionado != null)
            {
                hayPersonaSeleccionada = true;
            }
            return hayPersonaSeleccionada;
        }
        private async void EditarDepartamentoCommand_execute()
        {
            clsDepartamento departamentoPasado = new clsDepartamento(departamentoSeleccionado.ID, departamentoSeleccionado.Nombre);
            var miDiccionario = new Dictionary<string, object>
            {
                { "DepartamentoPasado", departamentoPasado }
            };
            await Shell.Current.GoToAsync("DetallesDepartamento", miDiccionario);
        }

        private async void AnadirDepartamentoCommand_execute()
        {
            await Shell.Current.GoToAsync("DetallesDepartamento");
        }

        private void ActualizarListaCommand_execute()
        {
            Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
            hiloActualizar.Start();
        }
        #endregion

        #region Métodos
        public async void actualizarDatos()
        {
            IsRefreshing = true;
            NotifyPropertyChanged(nameof(IsRefreshing));
            NotifyPropertyChanged(nameof(IsNotRefreshing));
            busquedaUsuario = "";
            NotifyPropertyChanged(nameof(BusquedaUsuario));
            try
            {
                listaDepartamentosBackup = clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
            }
            catch (Exception e)
            {
                bool volverAintentar = await Application.Current.MainPage.DisplayAlert("Error al cargar los departamentos", "'" + e.Message + "' XD'nt", "Recargar", "Salir");
                if (volverAintentar)
                {
                    Thread hiloActualizar = new Thread(new ThreadStart(actualizarDatos));
                    hiloActualizar.Start();
                }
            }
            listaDepartamentos = new ObservableCollection<clsDepartamento>(listaDepartamentosBackup);
            NotifyPropertyChanged(nameof(ListaDepartamentos));
            IsRefreshing = false;
            NotifyPropertyChanged(nameof(IsRefreshing));
            NotifyPropertyChanged(nameof(IsNotRefreshing));
        }

        public bool IsNotRefreshing { get { return !isRefreshing; } }
        #endregion
    }

}
