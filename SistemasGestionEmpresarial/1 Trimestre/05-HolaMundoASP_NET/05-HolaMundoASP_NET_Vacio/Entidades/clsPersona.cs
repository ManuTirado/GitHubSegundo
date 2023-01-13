using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidad_Persona
{
    public class clsPersona
    {
        #region Propiedades
        public string Nombre { get; set; }
        #endregion

        #region Constructores
        public clsPersona()
        {
            this.Nombre = "";
        }
        public clsPersona(string nombre)
        {
            this.Nombre = nombre;
        }
        #endregion
    }
}
