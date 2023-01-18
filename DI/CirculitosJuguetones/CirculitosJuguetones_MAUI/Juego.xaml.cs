using Entidades;
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

            _connection = new HubConnectionBuilder().WithUrl("http://localhost:5103/CirculitosJuguetones_HUB").Build();

            _connection.On<clsCirculo>("DibujarCirculo", (circulo) =>
            {
                Circulos.Add(DibujarCirculo(circulo, false));
            });

            _connection.StartAsync();
            clsCirculo circulo = new clsCirculo();
            Circulos.Add(DibujarCirculo(circulo, true));

            _connection.InvokeCoreAsync("EnviarCirculo", args: new[] { circulo });

            
        }

        public void OnBtnNuevoCirculo(object sender, EventArgs args)
        {
            clsCirculo circulo = new clsCirculo();
            Circulos.Add(DibujarCirculo(circulo, false));
            Task.Run(async () =>
            {
                await _connection.InvokeCoreAsync("EnviarCirculo", args: new[] { circulo });
            });
        }

        public void OnBtnMoverCirculo(object sender, EventArgs args)
        {
            AbsoluteLayout.SetLayoutBounds(Circulos[0], new Rect(100, 200, Circulos[0].Width, Circulos[0].Height));
            Circulos[0].Invalidate();
        }

        public GraphicsView DibujarCirculo(clsCirculo circulo, bool miCirculo)
        {
            GraphicsView graphicCirculo = new GraphicsView
            {
                Drawable = new drawableCirculo(circulo, miCirculo),
                WidthRequest = circulo.Radio * 4,
                HeightRequest = circulo.Radio * 4
            };
            contenedor.Add(graphicCirculo);

            AbsoluteLayout.SetLayoutBounds(graphicCirculo, new Rect(circulo.PosX, circulo.PosY, circulo.Radio, circulo.Radio));
            graphicCirculo.Invalidate();

            return graphicCirculo;
        }
    }
}