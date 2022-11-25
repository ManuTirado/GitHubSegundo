using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CRUD_Personas_Entidades
{
    public class clsDepartamento
    {
        #region Atributos
        public int ID { get; }

        public string Nombre { get; set; }
        #endregion


        #region Constructores
        public clsDepartamento(int id, string nombre)
        {
            ID = id;
            Nombre = nombre;
        }
        public clsDepartamento()
        {

        }
        #endregion
    }
}
