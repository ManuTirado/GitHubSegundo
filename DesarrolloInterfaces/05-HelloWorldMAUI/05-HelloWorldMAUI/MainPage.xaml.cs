using _05_HelloWorldMAUI_Clases;

namespace _05_HelloWorldMAUI;

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
            clsPersona persona = new clsPersona();
            persona.Nombre = txtNombre.Text;
            persona.Apellidos = await DisplayPromptAsync("Ingrese sus apellidos:","");
            await DisplayAlert("", $"Hola {persona.Nombre} {persona.Apellidos}", "OK");
        }
    }
}

