using Entidades;
using System.Collections.ObjectModel;
using Microsoft.AspNetCore.SignalR.Client;


namespace CirculitosJuguetones_MAUI
{

    public partial class Juego : ContentPage
    {
        private readonly HubConnection _connection;
        public ObservableCollection<clsCirculo> Circulos;

        public Juego()
        {
            InitializeComponent();
            Circulos = new ObservableCollection<clsCirculo>();
            Circulos.Add(new clsCirculo());
            ColeccionCirculos.ItemsSource = Circulos;

            _connection = new HubConnectionBuilder().WithUrl("http://localhost:5103/").Build();

            _connection.On<clsCirculo>("DibujarCirculo", (circulo) =>
            {
                Circulos.Add(circulo);
            });

            Task.Run(() =>
            {
                Dispatcher.Dispatch(async () =>
                await _connection.StartAsync());
            });
        }

        public void OnBtnNuevoCirculo (object sender, EventArgs args)
        {
            Circulos.Add(new clsCirculo());
        }
    }
}