namespace _07_Ejercicios.Views;

public partial class Pagina5 : ContentPage
{
	public Pagina5()
	{
		InitializeComponent();
	}

    private async void OnBotonPagina5(object sender, EventArgs e)
    {
        // Hacemos un pop para no hacer una nueva instancia de mainpage
        await Navigation.PopAsync();
    }
}