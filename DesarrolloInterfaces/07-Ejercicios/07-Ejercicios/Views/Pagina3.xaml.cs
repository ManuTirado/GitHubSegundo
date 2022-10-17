namespace _07_Ejercicios.Views;

public partial class Pagina3 : ContentPage
{
	public Pagina3()
	{
		InitializeComponent();
	}

    /// <summary>
    /// 
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
    private async void OnBotonPagina3(object sender, EventArgs e)
    {
        // Hacemos un pop para no hacer una nueva instancia de mainpage
        await Navigation.PopAsync();
    }
}