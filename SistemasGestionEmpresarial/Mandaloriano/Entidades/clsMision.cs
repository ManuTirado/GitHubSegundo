namespace Entidades
{
    public class clsMision
    {
        #region Propiedades
        public int? Id { get; set; }
        public string? Nombre { get; set; }
        public string? Descripcion { get; set; }
        public float? Recompensa { get; set; }
        #endregion

        #region Constructores
        public clsMision(int id, string nombre, string descripcion, float recompensa)
        {
            Id = id;
            Nombre = nombre;
            Descripcion = descripcion;
            Recompensa = recompensa;
        }

        public clsMision ()
        {
            Id = null;
        }
        #endregion
    }
}