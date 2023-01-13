namespace Entidades
{
    public class clsPersona
    {
        #region Atributos
        private int id;
        private string nombre;
        private string apellido;
        #endregion

        #region Propiedades
        public int Id { get { return id; } set { id = value; } }
        public string Nombre { get { return nombre; } set { nombre = value; } }
        public string Apellido { get { return apellido; } set { apellido = value; } }
        #endregion

        #region Constructores
        public clsPersona (int Id, string Nombre, string Apellido)
        {
            id = Id;
            nombre = Nombre;
            apellido = Apellido;
        }
        public clsPersona ()
        {

        }
        #endregion

    }
}