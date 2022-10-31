using _03_ClasesAVerSiAhoraVa;


namespace _03_Hello_World_MAUI
{
    public partial class MainPage : ContentPage
    {

        public MainPage()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Evento asociado al click del botón btnSaludar. Mostrara por pantalla un saludo más el nombre del Entry
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private async void OnBtnSaludar(object sender, EventArgs e)
        {
            if (txtNombre.Text.Length != 0)
            {
                clsPersona persona = new clsPersona(txtNombre.Text);
                await DisplayAlert("", "Hola " + persona.Nombre, "OK");
            }
        }
    }
}