using Entidades;

namespace Mandaloriano.Models.VM
{
    public class clsListadoMisionesVM
    {
        #region Propiedades
        public List<clsMision> listadoMisiones { get; set; }
        public clsMision/*Era de chill, aquí no se pone '?', si no => *fernando enfadado*😡 */ clsMision { get; set; }
        /* #FernandoTratameBienNoTeRiasDeMiNoMeArranquesLaPiel🎶🎶 */
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
