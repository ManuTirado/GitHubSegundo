using _07_Ejercicios.Views;
using _07_Pages_Clase;

namespace _07_Ejercicios;

public partial class MainPage : ContentPage
{
	public MainPage()
	{
		InitializeComponent();
	}

	private async void OnBotonMainPage(object sender, EventArgs e)
	{
      await Navigation.PushAsync(new PaginaTabbed());
    }

    private async void onBotonPagina4(object sender, EventArgs e)
    {
        clsPersona persona = new clsPersona(entry1.Text, entry2.Text);
        await Navigation.PushAsync(new Pagina4(persona));
    }

    private async void onBotonPagina5(object sender, EventArgs e)
    {
        clsPersona persona = new clsPersona(entry1.Text, entry2.Text);
        //await Navigation.PushAsync(new Pagina5(persona));
    }
}