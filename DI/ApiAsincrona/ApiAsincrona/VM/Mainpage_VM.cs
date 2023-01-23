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
        private List<clsPersona> listadoPersonas;

        public List<clsPersona> ListadoPersonas { get { return listadoPersonas; } }

        public Mainpage_VM()
        {
            listadoPersonas = new List<clsPersona>();
            rellenarListado();
        }

        private async void rellenarListado()
        {
            Task<List<clsPersona>> task = ApiAsincrona_BL.clsListadosPersonasBL.ListadoCompletoPersonasBL();
            task.Start();

            listadoPersonas =  await task;
        }
    }
}
