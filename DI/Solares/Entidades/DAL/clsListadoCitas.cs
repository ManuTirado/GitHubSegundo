using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades.DAL
{
    public static class clsListadoCitas
    {
        public static List<clsCita> obtenerListadoCompletoCitas ()
        {
            List<clsCita> citas = new List<clsCita>();
            citas.Add(new clsCita("Juan Heredia", "c/Marqués de Pickman 1", "616554433"));
            citas.Add(new clsCita("Álvaro Castro", "c/Marqués de Pickman 2", "626554433"));
            citas.Add(new clsCita("Francisco Fresco", "c/Marqués de Pickman 3", "636554433"));
            citas.Add(new clsCita("Diego Hurtado", "c/Marqués de Pickman 4", "646554433"));
            citas.Add(new clsCita("Pablo Sinkem", "c/Marqués de Pickman 5", "656554433"));
            citas.Add(new clsCita("Josema Estrada", "c/Marqués de Pickman 6", "666554433"));
            citas.Add(new clsCita("Javier Muñoa", "c/Marqués de Pickman 7", "676554433"));
            citas.Add(new clsCita("Manuel Pascual", "c/Marqués de Pickman 8", "686554433"));
            return citas;
        }
    }
}
