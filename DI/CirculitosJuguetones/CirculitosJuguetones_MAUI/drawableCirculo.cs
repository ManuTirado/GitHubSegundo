using Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CirculitosJuguetones_MAUI
{
    public class drawableCirculo : IDrawable
    {
        private clsCirculo circulo;
        private bool miCirculo;

        public clsCirculo Circulo { get { return circulo; } }

        public drawableCirculo()
        {
        }
        public drawableCirculo(clsCirculo circulo)
        {
            this.circulo = circulo;
        }
        public drawableCirculo(clsCirculo circulo, bool miCirculo)
        {
            this.circulo = circulo;
            this.miCirculo = miCirculo;
        }

        public void Draw(ICanvas canvas, RectF dirtyRect)
        {
            PathF path = new PathF();
            path.AppendCircle(dirtyRect.Center.X, dirtyRect.Center.Y, circulo.Radio);

            if (miCirculo)
            {
                canvas.StrokeColor = Colors.Yellow;
            } else
            {
                canvas.StrokeColor = Colors.Black;
            }
            
            canvas.StrokeSize = 3;
            canvas.FillColor = Color.FromArgb(circulo.ColorCirculo);

            canvas.FillPath(path);
            canvas.DrawPath(path);
        }
    }
}
