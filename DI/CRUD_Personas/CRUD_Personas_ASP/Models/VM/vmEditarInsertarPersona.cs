using CRUD_Personas_Entidades;

namespace CRUD_Personas_ASP.Models.VM
{
    public class vmEditarInsertarPersona
    {
        #region Atributos
        private clsPersona persona;
        private List<clsDepartamento> listaDepartmentos;
        #endregion

        #region Propiedades
        public clsPersona Persona { get { return persona; } set { persona = value; } }

        public List<clsDepartamento> ListaDepartamentos { get { return listaDepartmentos; } }
        #endregion

        #region Constructores
        public vmEditarInsertarPersona()
        {
            this.listaDepartmentos = CRUD_Personas_BL.Listados.clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
        }
        public vmEditarInsertarPersona(clsPersona persona)
        {
            this.persona = persona;
            this.listaDepartmentos = CRUD_Personas_BL.Listados.clsListadosDepartamentosBL.ListadoCompletoDepartamentosBL();
        }

        #endregion
    }
}
