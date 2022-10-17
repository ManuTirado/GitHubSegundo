using _07_Ejercicios.Views;

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
}