using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades.DAL
{
    public static class clsObtenerFotosCita
    {
        public static List<string> obtenerFotosCita ()
        {
            List<string> fotos = new List<string> ();
            fotos.Add("casa1.jpg");
            fotos.Add("casa2.jpg");
            fotos.Add("casa3.jpg");
            fotos.Add("casa4.jpg");
            fotos.Add("casa3.jpg");
            fotos.Add("casa2.jpg");
            fotos.Add("casa1.jpg");
            fotos.Add("casa1.jpg");
            fotos.Add("casa2.jpg");
            fotos.Add("casa3.jpg");
            return fotos;
        }
    }
}
