using Entidades;
using System.Collections.ObjectModel;
using Microsoft.AspNetCore.SignalR.Client;


namespace CirculitosJuguetones_MAUI
{

    public partial class Juego : ContentPage
    {
        private readonly HubConnection _connection;
        public List<GraphicsView> Circulos = new List<GraphicsView>();

        public Juego()
        {
            InitializeComponent();

            _connection = new HubConnectionBuilder().WithUrl("http://localhost:5103/").Build();

            _connection.On<clsCirculo>("DibujarCirculo", (circulo) =>
            {
                Circulos.Add(DibujarCirculo(circulo));
            });

            Task.Run(() =>
            {
                Dispatcher.Dispatch(async () =>
                await _connection.StartAsync());
            });

            Circulos.Add(DibujarCirculo(new clsCirculo()));  
        }

        public void OnBtnNuevoCirculo (object sender, EventArgs args)
        {
            Circulos.Add(DibujarCirculo(new clsCirculo()));
        }

        public void OnBtnMoverCirculo(object sender, EventArgs args)
        {
            AbsoluteLayout.SetLayoutBounds(Circulos[0], new Rect(100, 200, Circulos[0].Width, Circulos[0].Height));
            Circulos[0].Invalidate();
        }

        public GraphicsView DibujarCirculo (clsCirculo circulo)
        {
            GraphicsView graphicCirculo = new GraphicsView {
                Drawable = new drawableCirculo(circulo),
                WidthRequest = circulo.Radio*4,
                HeightRequest = circulo.Radio*4
            };
            contenedor.Add(graphicCirculo);

            AbsoluteLayout.SetLayoutBounds(graphicCirculo,new Rect(circulo.PosX, circulo.PosY,circulo.Radio, circulo.Radio));
            graphicCirculo.Invalidate();

            return graphicCirculo;
        }
    }
}