using System;
using System.Collections.Generic;
using System.ComponentModel;
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
        #endregion

        #region Constructores
        public clsPersona()
        {
            id = 1;
            nombre = "Manuel";
            apellido = "Triado";
        }
        public clsPersona(int Id, string Nombre, string Apellido)
        {
            id = Id;
            nombre = Nombre;
            apellido = Apellido;
        }
        #endregion

        #region Getters y Setters
        public string Nombre { get { return nombre; } set { nombre = value; } }
        public string Apellido { get { return apellido; } set { apellido = value; } }
        public int Id { get { return id; } set { id = value; } }
        #endregion
    }
}
