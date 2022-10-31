using Entidades;
using Entidades.DAL;

namespace Solares.Pages;

public partial class Citas : ContentPage
{
	public Citas()
	{
		InitializeComponent();
		List<clsCita> listadoCitas = clsListadoCitas.obtenerListadoCompletoCitas();
		listadoCitas.Sort((x, y) => x.Distancia.CompareTo(y.Distancia));
		ListViewCitas.ItemsSource = listadoCitas;
	}
}