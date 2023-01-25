using ApiAsincrona.Utilidades;
using CRUD_Personas_Entidades;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ApiAsincrona.VM
{
    public class Mainpage_VM : clsVMBase
    {
        private ObservableCollection<clsPersona> listadoPersonas;

        public ObservableCollection<clsPersona> ListadoPersonas { get { return listadoPersonas; } }

        public Mainpage_VM()
        {
            cargarDatos();
        }

        private async void cargarDatos()
        {
            listadoPersonas = new ObservableCollection<clsPersona>();
            listadoPersonas = new ObservableCollection<clsPersona>(await ApiAsincrona_BL.clsListadosPersonasBL.ListadoCompletoPersonasBL());
            NotifyPropertyChanged(nameof(ListadoPersonas));
        }
    }
}
