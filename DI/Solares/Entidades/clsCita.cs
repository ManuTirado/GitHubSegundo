namespace Entidades
{
    public class clsCita
    {
        #region Propiedades privadas
        private string nombreCliente;
        private string direccion;
        private float distancia;
        private string telefono;
        private string observaciones;
        private List<string> fotos;
        private bool apta;
        #endregion

        #region Contructores
        public clsCita ()
        {
            Random random = new Random();
            distancia = (float) (random.NextDouble() * (10 - (101)) + (101));
            nombreCliente = "";
            direccion = "";
            telefono = "";
            observaciones = "";
            fotos = new List<string>();
            apta = false;
        }

        public clsCita(string NombreCliente, string Direccion, string Telefono)
        {
            nombreCliente = NombreCliente;
            direccion = Direccion;
            telefono = Telefono;
            observaciones = "";
            fotos = new List<string>();
            apta = false;
        }
        #endregion

        #region Getters y Setter
        public string NombreCliente { get { return nombreCliente; } set { nombreCliente = value; } }
        public string Direccion { get { return direccion; } set { direccion = value; } }
        public string Telefono { get { return telefono; } set { telefono = value; } }
        public string Observaciones { get { return observaciones; } set { observaciones = value; } }
        public List<string> Fotos { get { return fotos; } set { fotos = value; } }
        public bool isApta { get { return apta;} set { apta = value; } }
        public float Distancia { get { return distancia; } set { distancia = value; } }
        #endregion
    }
}