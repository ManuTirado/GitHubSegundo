using Entidades;

namespace DAL
{
    public static class clsListadosMisiones
    {
        public static List<clsMision> obtenerListadoCompleto() {
            List<clsMision> misiones = new List<clsMision>();
            misiones.Add(new clsMision(1, "Rescate de Baby Yoda", "Debes hacerte con Grogu y llevárselo a Luke SkyWalker para su entrenamiento.", 5000));
            misiones.Add(new clsMision(2, "Recuperar armadura Beskar", "La armadura de Bershka ha sido robada. Debes encontrarla.", 2000));
            misiones.Add(new clsMision(3, "Planeta Sorgon", "Debes llevar a un niño de vuelta a su planeta natal “Sorgon”.", 500));
            misiones.Add(new clsMision(4, "Renacuajos", "Debes llevar a una Dama Rana y sus huevos de Tatooine a la luna del estuario Trask, donde su esposo fertilizará los huevos.", 500));
            return misiones;
        }
    }
}