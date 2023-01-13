
using _08_Layouts.Pages;

namespace _08_Layouts;

public partial class MainPage : ContentPage
{
	public MainPage()
	{
		InitializeComponent();
	}

    /// <summary>
    /// Proceso que crea una nueva instancia de la página paginaStackLayout y la
    /// pone por delante en la navegación
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
	private async void onBotonStackLayout(object sender, EventArgs e)
	{
		await Navigation.PushAsync(new paginaStackLayout());
	}

    /// <summary>
    /// Proceso que crea una nueva instancia de la página paginaFlexLayout y la
    /// pone por delante en la navegación
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
    private async void onFlexLayout(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaFlexLayout());
    }

    /// <summary>
    /// Proceso que crea una nueva instancia de la página paginaAbsoluteLayout y la
    /// pone por delante en la navegación
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
	private async void onAbsoluteLayout(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaAbsoluteLayout());
    }

    /// <summary>
    /// Proceso que crea una nueva instancia de la página paginaVariosLayouts y la
    /// pone por delante en la navegación
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
    private async void onVariosLayouts(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaVariosLayouts());
    }

    /// <summary>
    /// Proceso que crea una nueva instancia de la página paginaListadoPersonas y la
    /// pone por delante en la navegación
    /// </summary>
    /// <param name="sender"></param>
    /// <param name="e"></param>
    private async void onListadoPersonas(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaListadoPersonas());
    }
}

