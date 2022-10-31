using Entidades;

namespace DAL
{
    public class clsListadosPersonas
    {

        /// <summary>
        /// Función estática que devuelve un listado de objetos de la clase clsPersona.
        /// La idea sería que obtuviera esta lista de una BBDD pero actualmente devuelve una que nosotros le predefinamos.
        /// </summary>
        /// <returns></returns>
        public static List<clsPersona> obtenerListadoCompletoPersonas()
        {
            List<clsPersona> listadoCompletoPersonas = new List<clsPersona>();
            listadoCompletoPersonas.Add(new clsPersona(1, "Manuel", "Tirado", "manuel.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(1, "Álvaro", "Castro", "alvaro.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(2, "David", "Carvajal", "david.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(3, "Diego", "Urtado", "diego.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(4, "Pablo", "Sinkem", "pablo.jpeg"));
            listadoCompletoPersonas.Add(new clsPersona(5, "Gaspar", "Calero", "gaspar.jpg"));
            listadoCompletoPersonas.Add(new clsPersona(6, "Xian", "Xhen", "xianxen.jpg"));
            listadoCompletoPersonas.Add(new clsPersona(7, "Rosa", "Narvaez", "rosa.jpg"));
            listadoCompletoPersonas.Add(new clsPersona(8, "Julia", "Valles", "julia.jpg"));
            listadoCompletoPersonas.Add(new clsPersona(9, "Josh", "De-La-Torre", "josh.jpg"));
            listadoCompletoPersonas.Add(new clsPersona(10, "Roberto", "Romero", "roberto.jpg"));
            return listadoCompletoPersonas;
        }
    }
}
