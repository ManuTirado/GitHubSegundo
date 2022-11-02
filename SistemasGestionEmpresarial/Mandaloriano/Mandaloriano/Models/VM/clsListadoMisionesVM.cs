using Entidades;

namespace Mandaloriano.Models.VM
{
    public class clsListadoMisionesVM
    {
        #region Propiedades
        public List<clsMision> listadoMisiones { get; set; }
        public clsMision? clsMision { get; set; }
        #endregion

        #region Constructores
        public clsListadoMisionesVM(List<clsMision> listadoMisiones, clsMision clsMision)
        {
            this.listadoMisiones = listadoMisiones;
            this.clsMision = clsMision;
        }
        #endregion
    }
}
