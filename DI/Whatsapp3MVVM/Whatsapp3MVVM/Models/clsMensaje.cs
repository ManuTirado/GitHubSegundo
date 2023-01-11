using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Whatsapp3MVVM.Models
{
    public class clsMensaje
    {
        #region Propiedades
        public string Emisor { get; set; }
        public string Mensaje { get; set; }
        #endregion

        #region Constructores
        public clsMensaje()
        {
            Emisor = "";
            Mensaje = "";
        }
        public clsMensaje(string emisor, string mensaje)
        {
            Emisor = emisor;
            Mensaje= mensaje;
        }
        #endregion

    }
}
