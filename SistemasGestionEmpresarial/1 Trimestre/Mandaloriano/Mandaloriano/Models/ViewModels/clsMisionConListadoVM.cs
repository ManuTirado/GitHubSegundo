using Entidades;

namespace Mandaloriano.Models.ViewModels
{
    public class clsMisionConListadoVM
    {
        public clsMision? mision { get; set; }
        public List<clsMision>? listadoMisiones { get; set; }

        public clsMisionConListadoVM (clsMision? mision, List<clsMision>? listadoMisiones)
        {
            this.mision = mision;
            this.listadoMisiones = listadoMisiones;
        }
        public clsMisionConListadoVM ()
        {
            
        }
    }
}
