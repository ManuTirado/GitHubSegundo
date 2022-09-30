namespace _03_Hello_World_MAUI
{
    public partial class MainPage : ContentPage
    {

        public MainPage()
        {
            InitializeComponent();
        }

        private async void OnBtnSaludar(object sender, EventArgs e)
        {
            if (txtNombre.Text.Length != 0)
            {
                await DisplayAlert("", "Hola " + txtNombre.Text, "OK");
            }
        }
    }
}