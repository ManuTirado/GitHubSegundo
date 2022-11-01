namespace Entidades
{
    public class clsMision
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public string Descripcion { get; set; }
        public float Recompensa { get; set; }

        public clsMision(int id, string nombre, string descripcion, float recompensa)
        {
            Id = id;
            Nombre = nombre;
            Descripcion = descripcion;
            Recompensa = recompensa;
        }
    }
}