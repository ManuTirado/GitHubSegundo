using CRUD_Personas_BL.Listados;
using CRUD_Personas_Entidades;
using CRUD_Personas_MAUI.Models.Utilidades;
using Microsoft.Data.SqlClient;

namespace CRUD_Personas_MAUI.Models.VM
{
    [QueryProperty(nameof(DepartamentoSeleccionado), "DepartamentoPasado")]
    public class vmEditarInsertarDepartamento:clsVMBase
    {
        #region Atributos
        clsDepartamento departamentoSeleccionado;
        DelegateCommand guardarDepartamento; // Diferenciar entre inserción o actualización
        #endregion

        #region Propiedades
        public clsDepartamento DepartamentoSeleccionado
        {
            get { return departamentoSeleccionado; }
            set
            {
                departamentoSeleccionado = value;
                NotifyPropertyChanged(nameof(DepartamentoSeleccionado));
            }
        }
        public DelegateCommand GuardarDepartamento { get { guardarDepartamento = new DelegateCommand(GuardarDepartamentoCommand_execute, GuardarDepartamentoCommand_canExecute); ; return guardarDepartamento; } }
        #endregion

        #region Constructores
        public vmEditarInsertarDepartamento()
        {
            departamentoSeleccionado = new clsDepartamento();
        }
        #endregion

        #region Comandos
        private bool GuardarDepartamentoCommand_canExecute()
        {
            return true;
        }
        private async void GuardarDepartamentoCommand_execute()
        {
            if (departamentoSeleccionado.ID != 0)
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.EditarDepartamentoBL(departamentoSeleccionado.ID, departamentoSeleccionado);
                    await Application.Current.MainPage.DisplayAlert("Correcto", "Departamento editado correctamente", "OK");
                    await Shell.Current.GoToAsync("..");
                }
                catch (SqlException e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar editar el departamento", " Los campos marcados con * son obligatorios", "OK");
                }
            }
            else
            {
                try
                {
                    CRUD_Personas_BL.Manejadoras.clsManejadoraDepartamentosBL.InsertarDepartamentoBL(departamentoSeleccionado);
                    await Application.Current.MainPage.DisplayAlert("Correcto", "Departamento insertado correctamente", "OK");
                    await Shell.Current.GoToAsync("..");
                }
                catch (SqlException e)
                {
                    await Application.Current.MainPage.DisplayAlert("Error al intentar añadir el departamento", "Los campos marcados con * son obligatorios", "OK");
                }
            }
        }
        #endregion
    
}
}
