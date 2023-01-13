namespace MandalorianoMAUI
{
    public partial class MainPage : ContentPage
    {

        public MainPage()
        {
            InitializeComponent();
            lstListadoMisiones.ItemsSource = DAL.clsListadosMisiones.obtenerListadoCompleto();
        }

    }
}