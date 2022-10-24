
using _08_Layouts.Pages;

namespace _08_Layouts;

public partial class MainPage : ContentPage
{
	public MainPage()
	{
		InitializeComponent();
	}

	private async void onBotonStackLayout(object sender, EventArgs e)
	{
		await Navigation.PushAsync(new paginaStackLayout());
	}

    private async void onFlexLayout(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaFlexLayout());
    }

	private async void onAbsoluteLayout(object sender, EventArgs e)
    {
        await Navigation.PushAsync(new paginaAbsoluteLayout());
    }
}

