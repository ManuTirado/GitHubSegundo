using Microsoft.Maui;

namespace _06_EjercicioBotones;

public partial class MainPage : ContentPage
{
    bool boton3creado = false;
	public MainPage()
	{
		InitializeComponent();
	}

	private void OnBoton2Clicked (object sender, EventArgs e)
	{
        if (!boton3creado) {
            Button button = new Button
            {
                Text = "Boton3",
                VerticalOptions = LayoutOptions.Center,
                HorizontalOptions = LayoutOptions.Center,
                BackgroundColor = Colors.Blue,
                HeightRequest = 70,
                WidthRequest = 200,
                FontSize = 16,
                FontAttributes = FontAttributes.Bold,
                BorderColor = Colors.Yellow,
                Margin = 30
            };

            button.Clicked += (sender, args) =>
            {
                btnBoton2.Text = "Crear controles en tiempo de ejecución mola";
                vslBotones.Children.Remove(btnBoton1);
            };
            vslBotones.Children.Add(button);
            boton3creado = true;
        }
        else
        {
            DisplayAlert("", "El botón 3 ya ha sido creado", "OK");
        }
	}

}

