using CRUD_Personas_Entidades;

namespace CRUD_Personas_ASP.Models.VM
{
    public class vmEditarInsertarDepartamento
    {
        #region Atributos
        private clsDepartamento departamento;
        #endregion

        #region Propiedades
        public clsDepartamento Departamento { get { return departamento; } set { departamento = value; } }
        #endregion

        #region Constructores
        public vmEditarInsertarDepartamento()
        {

        }

        public vmEditarInsertarDepartamento(clsDepartamento departamento)
        {
            this.departamento = departamento;
        }
        #endregion
    }
}
