using Entidades;

namespace DAL
{
    public static class clsListadosPersonas
    {
        public static List<clsPersona> obtenerListadoCompleto()
        {
            List<clsPersona> listadoPersonas = new List<clsPersona>();
            listadoPersonas.Add(new clsPersona(1, "Manuel", "Tirado"));
            listadoPersonas.Add(new clsPersona(2, "Álvaro", "Castrol"));
            listadoPersonas.Add(new clsPersona(3, "Francisco Manuel", "Fresco"));
            listadoPersonas.Add(new clsPersona(4, "Diego", "Hurtado"));
            listadoPersonas.Add(new clsPersona(5, "Pablo", "Sinkem"));
            listadoPersonas.Add(new clsPersona(6, "Jose Manuel", "Entrada"));
            listadoPersonas.Add(new clsPersona(7, "Javier", "Munoa"));
            listadoPersonas.Add(new clsPersona(8, "Carmelo", "LaVoz"));
            listadoPersonas.Add(new clsPersona(9, "Marta", "TheBoss"));
            listadoPersonas.Add(new clsPersona(10, "Jesús", "DosHermanas"));
            listadoPersonas.Add(new clsPersona(11, "Fernando", "Apruebame <3"));
            listadoPersonas.Add(new clsPersona(12, "NPC", "Número1"));
            listadoPersonas.Add(new clsPersona(13, "NPC", "Número2"));
            listadoPersonas.Add(new clsPersona(14, "NPC", "Número3"));
            listadoPersonas.Add(new clsPersona(15, "NPC", "Número4"));
            listadoPersonas.Add(new clsPersona(16, "NPC", "Número5"));
            return listadoPersonas;
        }
    }
}