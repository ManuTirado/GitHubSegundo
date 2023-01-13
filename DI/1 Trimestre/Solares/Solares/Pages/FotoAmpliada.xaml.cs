namespace Solares.Pages;

public partial class FotoAmpliada : ContentPage
{
	public FotoAmpliada(string foto)
	{
		InitializeComponent();
        imgAmpliada.Source = foto;

    }
}