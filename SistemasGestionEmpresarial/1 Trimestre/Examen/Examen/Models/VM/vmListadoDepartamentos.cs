using Examen_Entidades;

namespace Examen_ASP.Models.VM
{
    public class vmListadoDepartamentos
    {
        #region Atributos
        private List<clsDepartamento> listadoDepartamentos;
        private List<clsPersona> listadoPersonas;
        #endregion

        #region Propiedades
        public List<clsDepartamento> ListadoDepartamentos { get { return listadoDepartamentos; } }
        public List<clsPersona> ListadoPersonas { get { return listadoPersonas; } }
        #endregion

        #region Constructores
        public vmListadoDepartamentos() {
            this.listadoDepartamentos = Examen_BL.Listados.clsListadosDepartamentosBL.obtenerListadoCompletoBL();
            this.listadoPersonas = Examen_BL.Listados.clsListadosPersonasBL.obtenerListadoCompletoBL();
        }

        #endregion
    }
}
