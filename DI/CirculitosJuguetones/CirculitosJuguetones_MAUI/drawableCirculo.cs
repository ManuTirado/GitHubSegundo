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

        public clsCirculo Circulo { get { return circulo; } }

        public drawableCirculo()
        {
        }
        public drawableCirculo(clsCirculo circulo)
        {
            this.circulo = circulo;
        }

        public void Draw(ICanvas canvas, RectF dirtyRect)
        {
            PathF path = new PathF();
            path.AppendCircle(dirtyRect.Center.X, dirtyRect.Center.Y, circulo.Radio);

            canvas.StrokeColor = Colors.Black;
            canvas.StrokeSize = 3;
            canvas.FillColor = Color.FromArgb(circulo.ColorCirculo.Name);

            canvas.FillPath(path);
            canvas.DrawPath(path);
        }
    }
}
