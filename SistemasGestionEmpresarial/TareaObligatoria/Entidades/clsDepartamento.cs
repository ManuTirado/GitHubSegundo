using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades
{
    public class clsDepartamento
    {
        public int IdDepartamento { get; set; }
        public string NombreDepartamento { get; set; }

        public clsDepartamento (int idDepartamento, string nombreDepartamento)
        {
            IdDepartamento = idDepartamento;
            NombreDepartamento = nombreDepartamento;
        }

        public clsDepartamento ()
        {

        }
    }
}
