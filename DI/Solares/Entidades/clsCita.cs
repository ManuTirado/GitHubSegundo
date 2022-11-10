using System;
using System.Drawing;

namespace Entidades
{
    public class clsCita
    {
        #region Propiedades privadas
        private string nombreCliente;
        private string direccion;
        private TimeOnly hora;
        private float distancia;
        private string telefono;
        private string observaciones;
        private List<string> fotos;
        private bool apta;
        #endregion

        #region Contructores
        public clsCita ()
        {
            distancia = (float) (new Random().NextDouble() * 100);
            nombreCliente = "";
            direccion = "";
            telefono = "";
            observaciones = "";
            fotos = new List<string>();
            apta = false;
        }

        public clsCita(string NombreCliente, TimeOnly Hora , string Direccion, string Telefono)
        {
            nombreCliente = NombreCliente;
            hora = Hora;
            direccion = Direccion;
            telefono = Telefono;
            observaciones = "";
            fotos = new List<string>();
            apta = false;
            distancia = (float)(new Random().NextDouble() * 100);
        }
        #endregion

        #region Getters y Setter
        public string NombreCliente { get { return nombreCliente; } set { nombreCliente = value; } }
        public string Direccion { get { return direccion; } set { direccion = value; } }
        public string Telefono { get { return telefono; } set { telefono = value; } }
        public string Observaciones { get { return observaciones; } set { observaciones = value; } }
        public List<string> Fotos { get { return fotos; } set { fotos = value; } }
        public bool IsApta { get { return apta;} set { apta = value; } }
        public float Distancia { get { return distancia; } set { distancia = value; } }
        public TimeOnly Hora { get { return hora; } set { hora = value; } }
        public string getHora { get { return hora.ToString("HH:ss"); } }
        #endregion
    }
}