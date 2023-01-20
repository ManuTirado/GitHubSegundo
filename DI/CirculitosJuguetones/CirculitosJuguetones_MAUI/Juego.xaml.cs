using Entidades;
using Microsoft.AspNetCore.SignalR.Client;

namespace CirculitosJuguetones_MAUI
{

    public partial class Juego : ContentPage
    {
        private readonly HubConnection _connection;
        private GraphicsView miCirculo;

        public Juego()
        {
            InitializeComponent();

            _connection = new HubConnectionBuilder().WithUrl("http://localhost:5103/CirculitosJuguetones_HUB").Build();

            _connection.On<clsCirculo>("DibujarCirculo", (circulo) =>
            {
                DibujarCirculo(circulo, false);
            });

            _connection.StartAsync();
            clsCirculo circulo = new clsCirculo();
            miCirculo = DibujarCirculo(circulo, true);

            _connection.InvokeCoreAsync("EnviarCirculo", args: new[] { circulo });
        }

        public void OnBtnMoverCirculo(object sender, EventArgs args)
        {
            int x = 0, y = 0;
            if (int.TryParse(txtX.Text, out x) && int.TryParse(txtY.Text, out y))
            {
                AbsoluteLayout.SetLayoutBounds(miCirculo, new Rect(x, y, miCirculo.Width, miCirculo.Height));
                miCirculo.Invalidate();
            } else
            {
                DisplayAlert("Alerta", "Introduzca valores válidos en los entrys 😡", "Ok");
                txtX.Text = "";
                txtY.Text = "";
            }
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


        /*
        void OnTapGestureRecognizerTapped(object sender, TappedEventArgs e)
        {
            // Position inside window
            Point? windowPosition = e.GetPosition(null);

            // Position relative to an Image
            Point? relativeToImagePosition = e.GetPosition(image);

            // Position relative to the container view
            Point? relativeToContainerPosition = e.GetPosition((View)sender);
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
        */
    }
}