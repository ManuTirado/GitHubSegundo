namespace _03_ClasesAVerSiAhoraVa
{
    public class clsPersona
    {
        #region Atributos
        private string nombre;
        #endregion

        #region Propiedades
        public string Nombre
        {
            get { return nombre; }
            set { nombre = value; }
        }
        #endregion

        #region Constructores
        public clsPersona()
        {
            this.nombre = "";
        }
        public clsPersona(string nombre)
        {
            this.nombre = nombre;
        }
        #endregion
    }
}