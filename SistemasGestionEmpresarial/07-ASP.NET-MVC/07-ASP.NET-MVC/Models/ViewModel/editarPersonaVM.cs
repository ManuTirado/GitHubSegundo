using _07_ASP.NET_MVC.Models.DAL;

namespace _07_ASP.NET_MVC.Models.ViewModel
{
    public class editarPersonaVM 
    {
        #region Atributos privados
        private List<clsDepartamento> listadoDepartamentos;
        private clsPersona persona;
        #endregion

        #region Contstructores
        public editarPersonaVM(int id)
        {
            listadoDepartamentos = clsListadosDepartamentos.listadoCompletoDepartamentos();
            persona = clsManejadorPersona.obtenerPersonaPorId(id);
        }
        #endregion

        #region Propiedades
        public List<clsDepartamento> ListadoDepartamentos { get { return listadoDepartamentos; }}
        public clsPersona Persona { get { return persona; } set { persona = value; } }
        #endregion
    }
}
