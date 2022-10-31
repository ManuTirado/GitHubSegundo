namespace Solares.Pages;

public partial class Login : ContentPage
{
	public Login()
	{
		InitializeComponent();
	}

	public async void onLoginClicked(object sender, EventArgs args)
	{
		await Navigation.PushAsync(new Pages.Citas());
	}
}