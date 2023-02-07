using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Examen_Entidades
{
    public class clsPersona
    {
        #region Atributos
        public int id { get; set; }
        public string nombre { get; set; }
        public string apellidos { get; set; }
        public string telefono { get; set; }
        public string direccion { get; set; }
        public string foto { get; set; }
        public DateTime fechaNacimiento { get; set; }
        public int idDepartamento { get; set; }
        #endregion

        #region Constructores
        public clsPersona(int id, string nombre, string apellido, string telefono, string direccion, string foto, DateTime fechaNacimiento, int iDDepartamento)
        {
            this.id = id;
            this.nombre = nombre;
            apellidos = apellido;
            this.telefono = telefono;
            this.direccion = direccion;
            this.foto = foto;
            this.fechaNacimiento = fechaNacimiento;
            idDepartamento = iDDepartamento;
        }
        public clsPersona()
        {

        }
        #endregion
    }
}
