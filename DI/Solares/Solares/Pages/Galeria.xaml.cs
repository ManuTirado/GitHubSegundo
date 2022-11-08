using Entidades.DAL;
namespace Solares.Pages;

public partial class Galeria : ContentPage
{
	public Galeria()
	{
        CollectionViewFotos.ItemsSource = clsObtenerFotosCita.obtenerFotosCita();
    }
}