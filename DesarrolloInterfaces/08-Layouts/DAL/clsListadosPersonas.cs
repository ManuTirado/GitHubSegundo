using Entidades;

namespace DAL
{
    public class clsListadosPersonas
    {
        public static List<clsPersona> obtenerListadoCompletoPersonas()
        {
            List<clsPersona> listadoCompletoPersonas = new List<clsPersona>();
            listadoCompletoPersonas.Add(new clsPersona(1, "Manuel", "Tirado", "Manuel.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(1, "Álvaro", "Castro", "Alvaro.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(2, "David", "Carvajal", "David.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(3, "Diego", "Urtado", "Diego.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(4, "Pablo", "Sinkem", "Pablo.jpeg"));
            return listadoCompletoPersonas;
        }
    }
}
