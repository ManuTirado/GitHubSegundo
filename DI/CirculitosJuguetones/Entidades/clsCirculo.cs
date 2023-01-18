using System.Drawing;

namespace Entidades
{
    public class clsCirculo
    {

        #region Propiedades
        private Color colorCirculo;
        private float posX;
        private float posY;
        private float radio;
        #endregion

        #region Atributos
        public Color ColorCirculo { get { return  colorCirculo; } set { colorCirculo = value; } }
        public float PosX { get { return posX; } set { posX = value; } }
        public float PosY { get { return posY; } set { posY = value; } }
        public float Radio { get { return radio; } set { radio = value; } }
        #endregion

        #region Constructores
        public clsCirculo()
        {
            Random r = new Random();
            this.colorCirculo = Color.FromArgb(r.Next(255), r.Next(255), r.Next(255));
            this.posX = r.Next(1100);
            this.posY = r.Next(600);
            this.radio = r.Next(20) + 20;
        }
        #endregion

        #region Métodos

        #endregion
    }
}