namespace Entidades
{
    public class clsCarta
    {
        #region Atributos
        public int id { get; set; }
        // public bool isVolteada { get; set; } (Como no esta -> ñapa)
        public string rutaImagenMostrada { get; set; }
        public string rutaImagenAnverso { get; set; }
        public string rutaImagenReverso { get; set; }
        public bool isAmigo { get; set; }
        #endregion

        #region Constructores
        public clsCarta()
        {

        }

        #endregion
    }
}