using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace U9_Ejrs_2.Models
{
    public class clsPersona : INotifyPropertyChanged
    {
        #region Propiedades privadas
        private int id;
        private string nombre;
        private string apellido;
        #endregion

        #region Constructores
        public clsPersona()
        {
            id = 1;
            nombre = "Manuel";
            apellido = "Tirado";
        }
        public clsPersona(int Id, string Nombre, string Apellido)
        {
            id = Id;
            nombre = Nombre;
            apellido = Apellido;
        }
        #endregion

        #region Getters y Setters
        public string Nombre 
        { 
            get { return nombre; }
            set 
            {
                nombre = value;
                onPropertyChanged("Nombre");
            } 
        }

        public string Apellido { get { return apellido; } set { apellido = value; } }
        public int Id { get { return id; } set { id = value; } }
        #endregion

        public event PropertyChangedEventHandler PropertyChanged;
        private void onPropertyChanged (string property)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(property));
            }
        }
    }
}
