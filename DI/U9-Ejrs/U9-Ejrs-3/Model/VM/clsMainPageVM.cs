using Entidades;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace U9_Ejrs_3.Model.VM
{
    public class clsMainPageVM: clsPersona,INotifyPropertyChanged
    {
        public clsMainPageVM()
        {
            
        }
        
        public new string Nombre { 
            get 
            { 
                return base.Nombre; 
            } 
            set 
            {
                base.Nombre = value; onPropertyChanged("Nombre"); 
            }
        }


        public event PropertyChangedEventHandler PropertyChanged;
        public void onPropertyChanged(string property)
        {
            if (PropertyChanged != null)
            {
                PropertyChanged(this, new PropertyChangedEventArgs(property));
            }
        }
    }
}
