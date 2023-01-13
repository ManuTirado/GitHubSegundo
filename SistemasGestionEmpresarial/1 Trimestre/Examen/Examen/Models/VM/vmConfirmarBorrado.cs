using Examen_Entidades;

namespace Examen_ASP.Models.VM
{
    public class vmConfirmarBorrado
    {
        #region Atributos
        private clsDepartamento departamento;
        private int numPersonasEnDept;
        private bool borrado;
        #endregion

        #region Propiedades
        public clsDepartamento Departamento { get { return departamento; } set { this.departamento = value; } }
        public int NumPersonasEnDept { get { return numPersonasEnDept; } set { this.numPersonasEnDept = value; } }
        public bool Borrado { get { return borrado; } set { this.borrado = value; } }
        #endregion

        #region Constructores
        public vmConfirmarBorrado() { }

        /// <summary>
        /// Construye el vm con el departamento pasado y obtiene el némero de personas en el departamento accediendo a la BBDD
        /// </summary>
        /// <param name="departamento"></param>
        public vmConfirmarBorrado(clsDepartamento departamento)
        {
            this.departamento = departamento;
            this.numPersonasEnDept = Examen_BL.Listados.clsListadosPersonasBL.obtenerPersonasDeDepartamentoBL(departamento.ID).Count();
            this.borrado = false;
        }


        #endregion
    }
}
