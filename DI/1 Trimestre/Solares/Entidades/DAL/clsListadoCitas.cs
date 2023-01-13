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
            Random random = new Random();
            List<clsCita> citas = new List<clsCita>();
            citas.Add(new clsCita("Juan Heredia", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 1", "616554433"));
            citas.Add(new clsCita("Álvaro Castro", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 2", "626554433"));
            citas.Add(new clsCita("Francisco Fresco", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 3", "636554433"));
            citas.Add(new clsCita("Diego Hurtado", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 4", "646554433"));
            citas.Add(new clsCita("Pablo Sinkem", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 5", "656554433"));
            citas.Add(new clsCita("Josema Estrada", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 6", "666554433"));
            citas.Add(new clsCita("Javier Muñoa", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 7", "676554433"));
            citas.Add(new clsCita("Manuel Pascual", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 8", "686554433"));
            citas.Add(new clsCita("Roberto Gambino", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 9", "696554433"));
            citas.Add(new clsCita("Pablo Alvorán", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 10", "706554433"));
            citas.Add(new clsCita("Chill Man", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 11", "716554433"));
            citas.Add(new clsCita("Pato Táctico", new TimeOnly(random.Next(24), random.Next(24)), "c/Marqués de Pickman 12", "726554433"));
            return citas;
        }
    }
}
