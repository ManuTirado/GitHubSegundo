using _07_Ejercicios;
using _07_Pages_Clase;

namespace _07_Ejercicios.Views;

public partial class Pagina4 : ContentPage
{
	public Pagina4(clsPersona persona)
	{
		InitializeComponent();
        lbPagina4.Text += persona.Nombre + " " + persona.Apellidos;

    }
    private async void OnBotonPagina4(object sender, EventArgs e)
    {
        // Hacemos un pop para no hacer una nueva instancia de mainpage
        await Navigation.PopAsync();
    }
}