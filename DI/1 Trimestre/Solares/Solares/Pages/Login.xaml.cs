namespace Solares.Pages;

public partial class Login : ContentPage
{
	public Login()
	{
		InitializeComponent();
	}

	public async void onLoginClicked(object sender, EventArgs args)
	{
		if (string.IsNullOrEmpty(txtUsuario.Text) && string.IsNullOrEmpty(txtContrasena.Text))
		{
			txtUsuario.PlaceholderColor= Colors.Red;
            txtContrasena.PlaceholderColor = Colors.Red;
        }
		else if (string.IsNullOrEmpty(txtUsuario.Text))
		{
            txtUsuario.PlaceholderColor = Colors.Red;
        }
		else if (string.IsNullOrEmpty(txtContrasena.Text))
		{
            txtContrasena.PlaceholderColor = Colors.Red;
        }
		else
		{
			txtUsuario.PlaceholderColor = Colors.Black;
			txtContrasena.PlaceholderColor = Colors.Black;
            await Navigation.PushAsync(new Pages.Citas());
        }
	}
}