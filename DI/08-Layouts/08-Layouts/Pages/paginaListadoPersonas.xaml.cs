using DAL;

namespace _08_Layouts.Pages;

public partial class paginaListadoPersonas : ContentPage
{
	public paginaListadoPersonas()
	{
		InitializeComponent();

		ListViewPersonas.ItemsSource = clsListadosPersonas.obtenerListadoCompletoPersonas();

    }
}