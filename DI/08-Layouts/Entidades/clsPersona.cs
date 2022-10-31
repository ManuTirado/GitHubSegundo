using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Entidades
{
    public class clsPersona
    {
        #region Propiedades privadas
        private int id;
        private string nombre;
        private string apellido;
        private string imagen;
        #endregion

        #region Constructores
        public clsPersona()
        {
            nombre = "";
            apellido = "";
        }
        public clsPersona(int Id, string Nombre, string Apellido, string Imagen)
        {
            id = Id;
            nombre = Nombre;
            apellido = Apellido;
            imagen = Imagen;
        }
        #endregion

        #region Getters y Setters
        public string Nombre { get { return nombre; } set { nombre = value; } }
        public string Apellido { get { return apellido; } set { apellido = value; } }
        public int Id { get { return id; } set { id = value; } }
        public string Imagen { get { return imagen; } set { imagen = value; } }
        #endregion
    }
}
