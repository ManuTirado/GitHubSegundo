namespace _07_Pages_Clase
{
    public class clsPersona
    {
        #region Propiedades
        public string Nombre { get; set; }
        public string Apellidos { get; set; }
        #endregion

        #region Constructores
        public clsPersona()
        {
            this.Nombre = "";
            this.Apellidos = "";
        }
        public clsPersona(string nombre, string apellidos)
        {
            this.Nombre = nombre;
            this.Apellidos = apellidos;
        }
        #endregion
    }
}